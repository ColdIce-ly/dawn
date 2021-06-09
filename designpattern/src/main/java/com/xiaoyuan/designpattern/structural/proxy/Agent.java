package com.xiaoyuan.designpattern.structural.proxy;

/**
 * @author : cold ice
 * @date : 2021-06-09 14:42
 */
public class Agent implements Concert {

    private final String name = "xx的经纪人";

    private Celebrity celebrity = null;

    public Agent bind(Celebrity celebrity) {
        this.celebrity = celebrity;
        return this;
    }

    private void preHold() {
        System.out.println(name + "选场地,收票钱----------");
    }

    @Override
    public void hold() {
        preHold();
        celebrity.hold();
        postHold();
    }

    private void postHold() {
        System.out.println(name + "收场----------");
    }

}
