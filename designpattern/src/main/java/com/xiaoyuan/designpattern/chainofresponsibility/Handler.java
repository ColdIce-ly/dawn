package com.xiaoyuan.designpattern.chainofresponsibility;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 18:22  
 */
public abstract class Handler {
    Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract boolean process(Request request);
}
