package com.xiaoyuan.designpattern.strategy.v3;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 18:08  
 */
public class Person {

    int age;
    int height;

    public Person(int age, int height) {
        this.age = age;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                '}';
    }
}
