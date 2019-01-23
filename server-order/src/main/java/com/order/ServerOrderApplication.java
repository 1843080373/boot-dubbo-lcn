package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.order.tx.HttpClient;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.order.mapper")
@EnableAspectJAutoProxy
public class ServerOrderApplication {
	public static ConfigurableApplicationContext c ;
	public static void main(String[] args) {
		 c = SpringApplication.run(ServerOrderApplication.class, args);
	}

	@Bean
	public HttpClient httpClient() {
		return new HttpClient();
	}
}

