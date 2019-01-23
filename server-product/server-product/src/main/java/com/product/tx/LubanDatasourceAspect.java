package com.product.tx;

import java.sql.Connection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LubanDatasourceAspect {

	@Around("execution(* javax.sql.DataSource.getConnection(..))")
	public Connection around(ProceedingJoinPoint point){
		try {
			if(LubanTransactionManager.getLubanTransaction()!=null) {
				System.out.println(1);
				return new LubanConnection((Connection)point.proceed(),LubanTransactionManager.getLubanTransaction());
			}else {
				System.out.println(2);
				return (LubanConnection) point.proceed();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
}