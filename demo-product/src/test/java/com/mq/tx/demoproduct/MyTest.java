package com.mq.tx.demoproduct;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.mq.tx.DemoProductApplication;
import com.mq.tx.entity.Product;
import com.mq.tx.mapper.ProductMapper;
import com.mq.tx.service.IMessageProducerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1() throws Exception {
    	productMapper.insert(new Product("产品2", new BigDecimal(19.8)));
    }
    
    @Test
    public void test2() throws Exception {
    	System.out.println(JSONObject.toJSONString(productMapper.selectAll()));
    }
    
    @Resource
    private IMessageProducerService messageProducer;
    @Test
    public void testSend() throws Exception {
        for (int x = 0; x < 10; x++) {
            this.messageProducer.sendMessage("study - " + x);
        }
    }
    
}