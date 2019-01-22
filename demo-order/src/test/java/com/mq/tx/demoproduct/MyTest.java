package com.mq.tx.demoproduct;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.mq.tx.DemoOrderApplication;
import com.mq.tx.entity.Order;
import com.mq.tx.mapper.OrderMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoOrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {

    @Autowired
    private OrderMapper OrderMapper;

    @Test
    public void test1() throws Exception {
    	OrderMapper.insert(new Order("产品2", new BigDecimal(19.8)));
    }
    
    @Test
    public void test2() throws Exception {
    	System.out.println(JSONObject.toJSONString(OrderMapper.selectAll()));
    }
}