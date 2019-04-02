package com.ysj.spike.service.impl;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.dao.UserDao;
import com.ysj.spike.domain.User;
import com.ysj.spike.exception.GlobalException;
import com.ysj.spike.redis.RedisService;
import com.ysj.spike.redis.impl.UserKey;
import com.ysj.spike.service.UserService;
import com.ysj.spike.utils.MD5Util;
import com.ysj.spike.utils.UUIDUtil;
import com.ysj.spike.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class UserServiceImpl implements UserService {

    private static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Override
    public User getById(Long id) {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    public boolean login(HttpServletResponse response, LoginVO loginVO) {
        if (loginVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String username = loginVO.getUsername();
        String formPass = loginVO.getPassword();
        //判断手机号是否存在
        User user = getById(Long.parseLong(username));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassTODBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        // 生成token
        addCookie(response,token, user);
        return true;
    }

    @Override
    public User getByToken(HttpServletResponse response, String token) {
        if (token.isEmpty()) {
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        if (user != null) {
            addCookie(response, token,user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        // 生成token
        redisService.set(UserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
