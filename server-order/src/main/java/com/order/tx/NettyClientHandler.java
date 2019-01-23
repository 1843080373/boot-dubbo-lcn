package com.order.tx;
import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@SuppressWarnings("deprecation")
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
	private ChannelHandlerContext ctx;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx=ctx;
	}
	@Override
	public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("接受到数据："+msg.toString());
		JSONObject jsonObject = (JSONObject) msg;

		String command = jsonObject.getString("command");// create 创建事务组 add 添加事务组
		String groupId = jsonObject.getString("groupId");// 事务组id
		System.out.println("接受到command="+command);
		//接受事务通知
		LubanTransaction lubanTransaction=LubanTransactionManager.getTxLubanTransaction(groupId);
		if(command.equals("commit")) {
			lubanTransaction.setTransactionType(TransactionType.commit);
		}else {
			lubanTransaction.setTransactionType(TransactionType.rollback);
		}
		lubanTransaction.getTask().signalTask();
	}

	public synchronized void call(JSONObject json) throws Exception {
		 ctx.writeAndFlush(JSONObject.toJSONString(json));
	}
}