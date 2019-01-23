package com.order.service;

import com.order.entity.User;

public interface UserService {
    void insert(User record);

	int insertSelective(User record);
}