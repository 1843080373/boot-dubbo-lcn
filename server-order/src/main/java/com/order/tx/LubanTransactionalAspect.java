package com.order.tx;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LubanTransactionalAspect implements Ordered{

	@Around("@annotation(com.order.tx.LubanTransactional)")
	public void invoke(ProceedingJoinPoint point) {
		MethodSignature seSignature=(MethodSignature) point.getSignature();
		Method method =seSignature.getMethod();
		LubanTransactional lubanTransactional=method.getAnnotation(LubanTransactional.class);
		System.out.println(lubanTransactional.isStart());
		//创建事务组
		String groupId=null;
		if(lubanTransactional.isStart()) {
			groupId=LubanTransactionManager.createLubanTransactionGroup();
		}else {
			groupId=LubanTransactionManager.getThreadLocalGroupId();
		}
		System.out.println("groupId["+groupId+"]");
		LubanTransaction lubanTransaction=LubanTransactionManager.createLubanTransaction(groupId);
		//提交事务到事务组
		 try {
			point.proceed();
			LubanTransactionManager.addLubanTransaction(lubanTransaction, lubanTransactional.isEnd(), TransactionType.commit);
		} catch (Exception e) {
			LubanTransactionManager.addLubanTransaction(lubanTransaction, lubanTransactional.isEnd(), TransactionType.rollback);
			e.printStackTrace();
		} catch (Throwable e) {
			LubanTransactionManager.addLubanTransaction(lubanTransaction, lubanTransactional.isEnd(), TransactionType.rollback);
			e.printStackTrace();
		}
	}

	@Override
	public int getOrder() {
		return 10000;
	}
}
