package com.ysj.spike.service.impl;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.dao.UserDao;
import com.ysj.spike.domain.User;
import com.ysj.spike.exception.GlobalException;
import com.ysj.spike.service.UserService;
import com.ysj.spike.utils.MD5Util;
import com.ysj.spike.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Long id) {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    public boolean login(LoginVO loginVO) {
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
        return true;
    }
}
