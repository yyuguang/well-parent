package com.lnzz.aclservice.controller;


import com.lnzz.aclservice.pojo.AclPermission;
import com.lnzz.aclservice.service.AclPermissionService;
import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Api(value = "菜单管理", tags = {"菜单管理相关接口"})
public class AclPermissionController {
    @Autowired
    private AclPermissionService permissionService;

    @ApiOperation(value = "获取全部菜单", notes = "获取全部菜单", httpMethod = "GET")
    @GetMapping("/indexAllPermission")
    public JsonResult indexAllPermission() {
        List<AclPermission> list = permissionService.queryAllMenu();
        return JsonResult.ok().data("list", list);
    }

    @ApiOperation(value = "递归删除菜单", notes = "递归删除菜单", httpMethod = "DELETE")
    @DeleteMapping("/remove")
    public JsonResult remove(@ApiParam(name = "id", value = "菜单", required = true)
                             @RequestParam String id) {
        permissionService.removeChildById(id);
        return JsonResult.ok();
    }

    @ApiOperation(value = "给角色分配权限", notes = "给角色分配权限", httpMethod = "POST")
    @PostMapping("/doAssign")
    public JsonResult doAssign(@ApiParam(name = "roleId", value = "权限id", required = true)
                               @RequestParam String roleId,
                               @ApiParam(name = "permissionId", value = "菜单id", required = true)
                               @RequestParam String[] permissionId) {
        permissionService.saveRolePermissionRelationShip(roleId, permissionId);
        return JsonResult.ok();
    }


    @ApiOperation(value = "根据角色获取菜单", notes = "根据角色获取菜单", httpMethod = "GET")
    @GetMapping("/toAssign")
    public JsonResult toAssign(@ApiParam(name = "roleId", value = "权限id", required = true)
                               @RequestParam String roleId) {
        List<AclPermission> list = permissionService.selectAllMenu(roleId);
        return JsonResult.ok().data("children", list);
    }


    @ApiOperation(value = "新增菜单", notes = "新增菜单", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody AclPermission permission) {
        permissionService.save(permission);
        return JsonResult.ok();
    }

    @ApiOperation(value = "编辑菜单", notes = "编辑菜单", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody AclPermission permission) {
        permissionService.updateById(permission);
        return JsonResult.ok();
    }

}

