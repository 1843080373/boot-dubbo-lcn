package com.product.service;

import com.product.entity.User;

public interface UserService {
	void insert(User record);

	int insertSelective(User record);
}