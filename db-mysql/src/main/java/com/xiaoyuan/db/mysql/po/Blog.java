package com.xiaoyuan.db.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : cold ice
 * @date : 2021-06-07 14:47
 * @description :
 */
@TableName("blog")
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = -2104707390433346376L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    public Blog() {
    }

    public Blog(String name) {
        this.name = name;
    }
}
