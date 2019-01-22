package com.mq.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mq.tx.mapper")
public class DemoProductApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DemoProductApplication.class);

	public static void main(String[] args) {
		logger.info("### Spring boot DubboServerApplication starter ...");
		SpringApplication.run(DemoProductApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("### Spring boot Dubbo Server start ok!");
	}

}

