package com.mq.tx.demoproduct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.api.UserWSService;
import com.boot.request.User;
import com.mq.tx.DemoOrderApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoOrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest2 {

   
    @Reference
    private UserWSService userWSService;

    @Test
    public void test3() throws Exception {
    	userWSService.saveUser(new User());
    }
}