package com.xiaoyuan.designpattern.flyweight;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 11:11  
 */
public class TreeNode {

    private int x;
    private int y;
    private TreeType treeType;

    public TreeNode(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }
}
