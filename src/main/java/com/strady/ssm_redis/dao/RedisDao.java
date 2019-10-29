package com.strady.ssm_redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: strady
 * @Date: 2019-10-16
 * @Time: 22:20:11
 * @Description:
 */
public class RedisDao {

    @Autowired
    private RedisTemplate template;

    public void add(String key, String value) {
        template.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return template.opsForValue().get(key);
    }

    public void delete(String key) {
        template.opsForValue().getOperations().delete(key);
    }
}
