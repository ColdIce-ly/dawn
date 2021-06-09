package com.xiaoyuan.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 11:12  
 */
public class TreeFactory {

    static Map<String, TreeType> map = new HashMap<>();

    public static TreeType getTreeTypeByName(String name, String data) {

        if (map.containsKey(name)) {
            return map.get(name);
        }
        TreeType treeType = new TreeType(name, data);
        map.put(name, treeType);
        return treeType;
    }

}
