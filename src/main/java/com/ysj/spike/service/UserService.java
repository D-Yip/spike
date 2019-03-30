package com.ysj.spike.service;

import com.ysj.spike.domain.User;

public interface UserService {

    User getById(int id);

    boolean tx();
}
