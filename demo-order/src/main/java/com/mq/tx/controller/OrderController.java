package com.mq.tx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.api.ProductWSService;
import com.boot.api.UserWSService;
import com.boot.request.ProductRequest;
import com.boot.request.User;

@RestController
public class OrderController {
	@Reference
	private UserWSService userService;
	@Reference
	private ProductWSService productWSService;

	@RequestMapping("/test")
	public User test() {
		return userService.saveUser(new User());

	}
	
	@RequestMapping("/product/insert")
	public ProductRequest product(ProductRequest productRequest) {
		return productWSService.save(productRequest);
	}
}
