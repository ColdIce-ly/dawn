package com.xiaoyuan.designpattern.chainofresponsibility;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 18:23  
 */
public class LoggingHandler extends Handler {

    public LoggingHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println(" 登录验证");
        if (request.isLoggedOn()) {
            Handler next = getNext();
            if (null == next) {
                return true;
            }
            if (!next.process(request)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
