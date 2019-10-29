package com.strady.ssm_redis.controller;

import com.strady.ssm_redis.entity.User;
import com.strady.ssm_redis.service.UserService;
import com.strady.ssm_redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: strady
 * @Date: 2019-10-16
 * @Time: 15:00:57
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/")
public class TestController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private RedisDao redisDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param id
     * @return ps:直接传到前台会导致中文变为问号，
     * 所以添加text/html;charset=utf-8
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String test(@RequestParam String id) {
        User user = userService.findUserById(id);
        log.info("nickName: " + user.getNickName());
        return user != null ? user.toString() : "null";
    }

    @RequestMapping(value = "/get")
    public String getRedis(@RequestParam String key) {
        Object x = redisUtil.get(key);
        log.info("x: " + x);
        return x != null ? x.toString() : "null";
    }

    @RequestMapping(value = "/set")
    public String set(@RequestParam String key,
                      @RequestParam String value) {
        log.info("key: " + key + ",value: " + value);
        redisUtil.set(key, value);
        return "success";
    }

    @RequestMapping(value = "/setSet")
    public String setSet(@RequestParam String key,
                         @RequestParam String value) {
        log.info("key: " + key + ",value: " + value);
        redisUtil.sAdd(key, value);
        if (redisUtil.sSize(key) > 5) {
            Set set = redisUtil.setMembers(key);
            for (int i = 0; i < set.size() - 6; i++) {
                redisUtil.sRemove((String) set.toArray()[i]);
            }
        }
        return "success";
    }

    @RequestMapping(value = "/getSet")
    public String getSet(@RequestParam String key) {
        log.info("key: " + key);
        Set<String> set = redisUtil.setMembers(key);
        set.forEach(str -> {
            System.out.println(str.toString());
        });
        return set != null ? set.toString() : "null";
    }

    @RequestMapping(value = "/type")
    public String type(@RequestParam String key) {
        DataType dataType = redisUtil.type(key);
        log.info("dataType: " + dataType);
        return "success";
    }

    @RequestMapping(value = "/check")
    public String check() {
        Long times = 0L;
        redisUtil.setIfAbsent("times", times.toString());
        redisUtil.expire("times", 10, TimeUnit.MINUTES);
        times = redisUtil.incrBy("times", 1);
        return times.toString();
    }


    public void test() {
        Map<String, Object> map = new Hashtable<>();
        map.put("s", "test");
    }
}
