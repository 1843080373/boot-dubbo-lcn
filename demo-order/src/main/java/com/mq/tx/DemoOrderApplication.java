package com.mq.tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mq.tx.mapper")
public class DemoOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOrderApplication.class, args);
	}

}

