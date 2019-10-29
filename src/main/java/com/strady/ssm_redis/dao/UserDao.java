package com.strady.ssm_redis.dao;

import com.strady.ssm_redis.entity.User;

/**
 * @Author: strady
 * @Date: 2019-10-16
 * @Time: 16:02:09
 * @Description:
 */
public interface UserDao {

    User findUserById(String id);
}
