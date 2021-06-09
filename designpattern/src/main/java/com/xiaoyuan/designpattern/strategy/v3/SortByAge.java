package com.xiaoyuan.designpattern.strategy.v3;

import java.util.Comparator;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 18:08  
 */
public class SortByAge implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        }
        return 0;
    }
}
