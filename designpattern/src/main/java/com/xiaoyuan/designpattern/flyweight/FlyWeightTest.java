package com.xiaoyuan.designpattern.flyweight;

/**
 * @author : liyuan  
 *  享元模式  运用共享技术有效地支持大量细粒度的对象;如果系统有大量类似的对象，可以节省大量的内存及CPU资源
 * @date : 2020/9/23 11:12  
 */
public class FlyWeightTest {
    public static void main(String[] args) {
        TreeType treeTypeByName = TreeFactory.getTreeTypeByName("xxx", "yyyyy");
        TreeNode treeNode1 = new TreeNode(0, 0, treeTypeByName);
        TreeNode treeNode2 = new TreeNode(4, 6, treeTypeByName);
    }
}