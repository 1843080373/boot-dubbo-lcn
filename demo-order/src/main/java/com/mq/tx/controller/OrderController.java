package com.mq.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mq.tx.entity.Order;
import com.mq.tx.service.OrderLocalService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderLocalService orderLocalService;

	@RequestMapping("/order/insert")
	public Order order(Order order) {
		return orderLocalService.save(order);
	}
}
