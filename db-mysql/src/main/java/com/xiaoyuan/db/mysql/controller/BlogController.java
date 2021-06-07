package com.xiaoyuan.db.mysql.controller;

import com.xiaoyuan.db.mysql.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : cold ice
 * @date : 2021-06-07 14:54
 * @description :
 */
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/test/for/transaction")
    public Object save() {
        blogService.save();
        return null;
    }

}
