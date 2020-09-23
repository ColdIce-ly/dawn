package com.xiaoyuan.designpattern.singleton.v3;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author : liyuan  
 * @description : 反射攻击
 * @date : 2020/9/23 13:51  
 */
public class InnerClassSingleton implements Serializable {

    // private static final long serialVersionUID = 6922639953390195232L;
    // private  static final long serialVersionUID = 42L;
    public static String name = "yyy";
    public static String name1 = "yyy";
    public static String name2 = "yyy";

    static {
        System.out.println(" InnerClassSingleton "); //  1
    }

    private InnerClassSingleton() {

        if (SingletonHolder.instance != null) {
            throw new RuntimeException(" 不允许多个实例。");
        }

    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }

    Object readResolve() throws ObjectStreamException {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();

        static {
            System.out.println(" SingletonHolder ");// 2
        }
    }
}
