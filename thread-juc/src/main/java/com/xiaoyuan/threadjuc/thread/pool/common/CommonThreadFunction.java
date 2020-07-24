package com.xiaoyuan.threadjuc.thread.pool.common;

/**
 * @author : liyuan  
 * @description : 线程池专用
 * @date :   
 */
@FunctionalInterface
public interface CommonThreadFunction {

    /**
     * @description 无参数,无返回值
     */
    void perform();
}
