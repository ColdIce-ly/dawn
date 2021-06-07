package com.xiaoyuan.db.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyuan.db.mysql.mapper")
public class DbMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbMysqlApplication.class, args);
    }

}
