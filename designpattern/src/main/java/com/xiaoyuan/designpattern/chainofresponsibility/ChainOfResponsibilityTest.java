package com.xiaoyuan.designpattern.chainofresponsibility;

/**
 * @author : liyuan  
 * @description :责任链模式 为请求创建了一个接收者对象的链。
 * 应用场景：
 * 一个请求的处理需要多个对象当中的一个或几个协作处理。
 * 优点:
 * 1.请求的发送者和接受者解耦
 * 2.可以控制执行顺序
 * 3.符合开闭原则和单一职责原则
 * @date : 2020/9/23 18:25  
 */
public class ChainOfResponsibilityTest {
    public static void main(String[] args) {

        Request request = new Request.RequestBuilder().frequentOk(false).loggedOn(false).build();


        RequestFrequentHandler requestFrequentHandler = new RequestFrequentHandler(new LoggingHandler(null));

        if (requestFrequentHandler.process(request)) {

            System.out.println(" 正常业务处理");
        } else {
            System.out.println(" 访问异常");
        }

    }
}






