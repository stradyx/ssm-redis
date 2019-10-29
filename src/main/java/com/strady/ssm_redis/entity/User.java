package com.strady.ssm_redis.entity;

import lombok.Data;

/**
 * @Author: strady
 * @Date: 2019-10-16
 * @Time: 16:00:20
 * @Description:
 */
@Data
public class User {

    private String id;

    private String userName;

    private String nickName;

    private String password;

    private String salt;

    private String mobile;

    private Long createTime;
}
