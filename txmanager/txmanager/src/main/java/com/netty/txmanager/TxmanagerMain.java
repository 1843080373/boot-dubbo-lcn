package com.netty.txmanager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class TxmanagerMain {
	private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;

	private static final int BIZTHREADSIZE = 100;
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

	public static void start(String hostname, Integer port) {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ObjectEncoder());
                    ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE,
                            ClassResolvers.cacheDisabled(null)));
                    ch.pipeline().addLast(new NettyServerHandler());
				}

			});
			System.out.println("��TCP������IP��" + hostname + "��TCP������PORT��" + port);
			ChannelFuture f = bootstrap.bind(hostname, port).sync();
			f.channel().closeFuture().sync();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		start("127.0.0.1", 8888);
	}

}

