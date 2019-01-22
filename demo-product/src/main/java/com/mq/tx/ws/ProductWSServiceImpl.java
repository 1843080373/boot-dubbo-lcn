package com.mq.tx.ws;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.api.ProductWSService;
import com.boot.request.ProductRequest;
import com.mq.tx.entity.Product;
import com.mq.tx.mapper.ProductMapper;
@Service
public class ProductWSServiceImpl implements ProductWSService {

	@Autowired
	private ProductMapper productMapper;
	@Override
	public ProductRequest save(ProductRequest productRequest) {
		Product p=new Product();
		BeanUtils.copyProperties(productRequest, p);
		productMapper.insert(p);
		return productRequest;
	}

}
