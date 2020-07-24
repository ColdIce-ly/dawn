package com.xiaoyuan.threadjuc.thread.pool.common;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/7/24 18:03  
 */
@FunctionalInterface
public interface ThreadConsumer<T> {


    /**
     * @description 1参数,无返回值
     */
    void perform(T t);
}
