package com.ysj.spike.service;

import com.ysj.spike.domain.User;
import com.ysj.spike.vo.LoginVO;

public interface UserService {

    User getById(Long id);

    boolean login(LoginVO loginVO);
}
