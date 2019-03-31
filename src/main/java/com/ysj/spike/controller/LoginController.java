package com.ysj.spike.controller;

import com.ysj.spike.base.Result;
import com.ysj.spike.service.UserService;
import com.ysj.spike.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public Result<Boolean> login(HttpServletResponse response, @RequestBody @Valid LoginVO loginVO) {
        // 登录
        userService.login(response, loginVO);
        return Result.success(true);
    }
}
