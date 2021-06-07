package com.xiaoyuan.db.mysql.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyuan.db.mysql.mapper.BlogMapper;
import com.xiaoyuan.db.mysql.po.Blog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author : cold ice
 * @date : 2021-06-07 14:51
 * @description :
 */
@Service
public class BlogService extends ServiceImpl<BlogMapper, Blog> {

    @Transactional(rollbackFor = Exception.class)
    public void save() {

        int count = 10;

        for (int i = 0; i < count; i++) {
            save2(i);
        }


    }

    public void save2(int i) {

        if (i % 5 == 0) {
            throw new RuntimeException();
        }

        baseMapper.insert(new Blog(UUID.randomUUID().toString()));

    }


}
