package com.strady.ssm_redis.service;

import com.strady.ssm_redis.entity.User;

/**
 * @Author: strady
 * @Date: 2019-10-16
 * @Time: 16:06:10
 * @Description:
 */
public interface UserService {

    User findUserById(String id);
}
