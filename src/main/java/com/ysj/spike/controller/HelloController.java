package com.ysj.spike.controller;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import com.ysj.spike.domain.User;
import com.ysj.spike.redis.RedisService;
import com.ysj.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/rest")
    public Result<String> rest(){
        return Result.success("hi");
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/db")
    public Result db(){
        User user = userService.getById(1);
        if (user == null) return Result.error(CodeMsg.SERVER_ERROR);
        return Result.success(user);
    }

    @RequestMapping(value = "/insert")
    public Result insert(){
        boolean tx = userService.tx();
        return Result.success(tx);
    }

    @RequestMapping(value = "/redis/get")
    public Result<User> redisGet() {
        User v1 = redisService.get("key2",User.class);
        return Result.success(v1);
    }

    @RequestMapping(value = "/redis/set")
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(22);
        user.setName("Jack");
        boolean v1 = redisService.set("key2",user);
        return Result.success(v1);
    }
}
