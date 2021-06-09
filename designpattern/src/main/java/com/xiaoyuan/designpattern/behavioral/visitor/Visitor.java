package com.xiaoyuan.designpattern.behavioral.visitor;

interface Visitor {
    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);
}