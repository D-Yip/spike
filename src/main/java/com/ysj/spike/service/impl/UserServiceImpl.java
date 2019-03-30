package com.ysj.spike.service.impl;

import com.ysj.spike.dao.UserDao;
import com.ysj.spike.domain.User;
import com.ysj.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public boolean tx() {
        User user1 = new User();
        user1.setId(2);
        user1.setName("2222");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("2222");
        userDao.insert(user2);

        return true;
    }
}
