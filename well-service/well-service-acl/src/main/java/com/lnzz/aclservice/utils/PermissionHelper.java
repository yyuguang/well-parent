package com.lnzz.aclservice.utils;

import com.lnzz.aclservice.pojo.AclPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：PermissionHelper
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 12:21
 * @Description 根据权限数据构建菜单数据
 */
public class PermissionHelper {
    /**
     * 使用递归方法建菜单
     *
     * @param treeNodes
     * @return
     */
    public static List<AclPermission> build(List<AclPermission> treeNodes) {
        List<AclPermission> trees = new ArrayList<>();
        for (AclPermission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static AclPermission findChildren(AclPermission treeNode, List<AclPermission> treeNodes) {
        treeNode.setChildren(new ArrayList<AclPermission>());

        for (AclPermission it : treeNodes) {
            if (treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
