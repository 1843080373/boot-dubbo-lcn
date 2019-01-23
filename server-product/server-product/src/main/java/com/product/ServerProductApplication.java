package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.product.mapper")
public class ServerProductApplication {
	public static ConfigurableApplicationContext c ;
	public static void main(String[] args) {
		c=SpringApplication.run(ServerProductApplication.class, args);
	}

}

