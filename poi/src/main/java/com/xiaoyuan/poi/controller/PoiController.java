package com.xiaoyuan.poi.controller;


import com.xiaoyuan.poi.po.Person;
import com.xiaoyuan.poi.util.fileio.ExcelBean;
import com.xiaoyuan.poi.util.fileio.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
public class PoiController {

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞", "1", new Date());
        Person person2 = new Person("娜美", "2", new Date());
        Person person3 = new Person("索隆", "1", new Date());
        Person person4 = new Person("小狸猫", "1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        List<ExcelBean> excel = new ArrayList<>();
        //设置标题栏
        excel.add(new ExcelBean("姓名", "name", 0));
        excel.add(new ExcelBean("性别", "sex", 0));
        excel.add(new ExcelBean("生日", "birthday", 0));
        String sheetName = "测试111";
        //调用ExcelUtil的方法
        ExcelUtil.dataOutPrintToExcel(Person.class, personList, excel, sheetName, response);
    }
}