package com.xiaoyuan.designpattern.prototype;

import java.io.Serializable;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:58  
 */
public class BaseInfo implements Cloneable, Serializable {
    private String companyName;

    public BaseInfo(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return ((BaseInfo) super.clone());
    }

    @Override
    public String toString() {
        return hashCode() + " ]BaseInfo{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
