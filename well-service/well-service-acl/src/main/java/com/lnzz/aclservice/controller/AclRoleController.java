package com.lnzz.aclservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.aclservice.pojo.AclRole;
import com.lnzz.aclservice.service.AclRoleService;
import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/admin/acl/role")
@Api(value = "角色管理", tags = {"角色管理相关接口"})
public class AclRoleController {

    @Autowired
    private AclRoleService roleService;


    @ApiOperation(value = "获取角色分页列表", notes = "获取角色分页列表", httpMethod = "GET")
    @GetMapping("/index/{page}/{limit}")
    public JsonResult index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            AclRole role) {
        Page<AclRole> pageParam = new Page<>(page, limit);
        QueryWrapper<AclRole> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name", role.getRoleName());
        }
        roleService.page(pageParam, wrapper);
        return JsonResult.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取角色", notes = "获取角色", httpMethod = "GET")
    @GetMapping("/get")
    public JsonResult get(@ApiParam(name = "id", value = "角色id", required = true)
                          @RequestParam String id) {
        AclRole role = roleService.getById(id);
        return JsonResult.ok().data("item", role);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody AclRole role) {
        roleService.save(role);
        return JsonResult.ok();
    }

    @ApiOperation(value = "编辑角色", notes = "编辑角色", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult updateById(@RequestBody AclRole role) {
        roleService.updateById(role);
        return JsonResult.ok();
    }

    @ApiOperation(value = "删除角色", notes = "删除角色", httpMethod = "DELETE")
    @DeleteMapping("/remove")
    public JsonResult remove(@ApiParam(name = "id", value = "角色id", required = true)
                             @RequestParam String id) {
        roleService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation(value = "根据id列表删除角色", notes = "根据id列表删除角色", httpMethod = "DELETE")
    @DeleteMapping("/batchRemove")
    public JsonResult batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return JsonResult.ok();
    }
}