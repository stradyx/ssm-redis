package com.strady.ssm_redis.service.impl;

import com.strady.ssm_redis.dao.UserDao;
import com.strady.ssm_redis.entity.User;
import com.strady.ssm_redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: strady
 * @Date: 2019-10-16
 * @Time: 16:06:34
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }
}
