package com.xiaoyuan.designpattern.behavioral.interpreter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//终结符表达式类
public class TerminalExpression implements Expression {
    private Set<String> set = new HashSet<>();
    public TerminalExpression(String[] data) {
        Collections.addAll(set, data);
    }
    @Override
    public boolean interpret(String info) {
        return set.contains(info);
    }
}