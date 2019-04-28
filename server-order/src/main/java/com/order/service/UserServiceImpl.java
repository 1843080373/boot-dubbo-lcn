package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.User;
import com.order.mapper.UserMapper;
import com.order.tx.HttpClient;
import com.order.tx.LubanTransactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private HttpClient httpClient;

	@Override
	@Transactional
	@LubanTransactional(isStart=true)
	public void insert(User record) {
		httpClient.get("http://127.0.0.1:8090/add?name=aaa");
		userMapper.insert(record);
		//System.out.println(1/0);
	}

	@Override
	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

}
