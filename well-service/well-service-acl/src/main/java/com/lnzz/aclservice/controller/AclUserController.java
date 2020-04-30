package com.lnzz.aclservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.aclservice.pojo.AclUser;
import com.lnzz.aclservice.service.AclRoleService;
import com.lnzz.aclservice.service.AclUserService;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.commonutils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/admin/acl/user")
@Api(value = "用户管理", tags = {"用户管理相关接口"})
public class AclUserController {

    @Autowired
    private AclUserService userService;
    @Autowired
    private AclRoleService roleService;


    @ApiOperation(value = "获取管理用户分页列表", notes = "获取管理用户分页列表", httpMethod = "GET")
    @GetMapping("/index/{page}/{limit}")
    public JsonResult index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            AclUser userQueryVo) {
        Page<AclUser> pageParam = new Page<>(page, limit);
        QueryWrapper<AclUser> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username", userQueryVo.getUsername());
        }

        IPage<AclUser> pageModel = userService.page(pageParam, wrapper);
        return JsonResult.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    @ApiOperation(value = "新增管理用户", notes = "新增管理用户", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody AclUser user) {
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        userService.save(user);
        return JsonResult.ok();
    }

    @ApiOperation(value = "编辑管理用户", notes = "编辑管理用户", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody AclUser user) {
        userService.updateById(user);
        return JsonResult.ok();
    }

    @ApiOperation(value = "删除管理用户", notes = "删除管理用户", httpMethod = "DELETE")
    @DeleteMapping("remove")
    public JsonResult remove(@ApiParam(name = "id", value = "用户id", required = true)
                             @RequestParam String id) {
        userService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation(value = "根据id列表删除管理用户", notes = "根据id列表删除管理用户", httpMethod = "DELETE")
    @DeleteMapping("/batchRemove")
    public JsonResult batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return JsonResult.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据", notes = "根据用户获取角色数据", httpMethod = "GET")
    @GetMapping("/toAssign")
    public JsonResult toAssign(@ApiParam(name = "userId", value = "用户id", required = true)
                               @RequestParam String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return JsonResult.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色", notes = "根据用户分配角色", httpMethod = "POST")
    @PostMapping("/doAssign")
    public JsonResult doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        roleService.saveUserRoleRelationShip(userId, roleId);
        return JsonResult.ok();
    }
}



