package com.ysj.spike.service;

import com.ysj.spike.domain.User;
import com.ysj.spike.vo.LoginVO;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    User getById(Long id);

    boolean updatePassword(String token, long id,String passwordNew);

    boolean login(HttpServletResponse response, LoginVO loginVO);

    User getByToken(HttpServletResponse response, String token);
}
