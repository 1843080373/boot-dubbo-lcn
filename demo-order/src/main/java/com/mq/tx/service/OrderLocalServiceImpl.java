package com.mq.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.api.ProductWSService;
import com.boot.request.ProductRequest;
import com.mq.tx.entity.Order;
import com.mq.tx.mapper.OrderMapper;

@Service
public class OrderLocalServiceImpl implements OrderLocalService {
	@Autowired
	private OrderMapper orderMapper;
	@Reference
	private ProductWSService productWSService;

	@Override
	@Transactional
	public Order save(Order order) {
		orderMapper.insert(order);
		productWSService.save(new ProductRequest(order.getName() + "-产品", order.getAccount()));
		return order;
	}

}
