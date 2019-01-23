package com.order.tx;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

@Component
public class NettyClient implements InitializingBean {
	private NettyClientHandler nettyClientHandler = null;

	public String start(String host, Integer port) {
		nettyClientHandler = new NettyClientHandler();
		Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			bootstrap.group(group);
			bootstrap.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
					ch.pipeline().addLast(nettyClientHandler);
				}
			});
			bootstrap.connect(host, port).sync();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void send(JSONObject jsonObject) {
		try {
			nettyClientHandler.call(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start("127.0.0.1", 8888);
		System.out.println("连接上了");
	}
}
