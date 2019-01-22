package com.mq.tx.ws;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.api.UserWSService;
import com.boot.request.User;

/**
 * Created by Jaycekon on 2017/9/19.
 */
@Service
public class UserWSServiceImpl implements UserWSService {

    @Override
    public User saveUser(User user) {
        user.setId(1);
        System.out.println(user.toString());
        return user;
    }
}