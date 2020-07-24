package com.xiaoyuan.threadjuc.thread.pool.common;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/7/24 17:32  
 */
@FunctionalInterface
public interface ThreadSupplier<R> {

    /**
     * @description 无参数,1返回值
     */
    R perform();
}
