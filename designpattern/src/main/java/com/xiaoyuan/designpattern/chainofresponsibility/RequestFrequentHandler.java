package com.xiaoyuan.designpattern.chainofresponsibility;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 18:23  
 */
public class RequestFrequentHandler extends Handler {

    public RequestFrequentHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {

        System.out.println("访问频率控制.");
        if (request.isFrequentOk()) {
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
