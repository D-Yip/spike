package com.ysj.spike.redis.impl;

public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey token = new UserKey("tk");

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("name");
}
