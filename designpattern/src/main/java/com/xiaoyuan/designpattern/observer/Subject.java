package com.xiaoyuan.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 18:19  
 */
public class Subject {
    // 容器
    private List<Observer> container = new ArrayList<>();

    // add
    public void addObserver(Observer observer) {
        container.add(observer);
    }

    // remove
    public void remove(Observer observer) {
        container.remove(observer);
    }

    public void notifyObserver(Object object) {
        for (Observer item : container) {
            item.update(object);
        }

    }
}
