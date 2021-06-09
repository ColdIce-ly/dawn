package com.xiaoyuan.designpattern.create.singleton.v3;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 反射攻击
 *
 * @author : liyuan  
 * @date : 2020/9/23 13:51  
 */
public class InnerClassSingleton implements Serializable {

    private static final long serialVersionUID = 4496599561786583864L;

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
            System.out.println(" SingletonHolder ");
        }
    }
}
