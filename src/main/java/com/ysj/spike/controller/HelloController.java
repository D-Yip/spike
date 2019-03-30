package com.ysj.spike.controller;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import com.ysj.spike.domain.User;
import com.ysj.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

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
}
