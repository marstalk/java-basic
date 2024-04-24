package com.ljc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * 固定长度的消息
 */
public class CFixedLengthServer {
    public static void main(String[] args) {
        int port = 9999;
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(new NioEventLoopGroup(1), new NioEventLoopGroup()).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel channel) throws Exception {
                    // 固定长度9个字节为一个消息。
                    channel.pipeline().addLast(new FixedLengthFrameDecoder(9));
                    channel.pipeline().addLast(new PrintHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("server started on port " + port);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class PrintHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            InetSocketAddress remoteAddress = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
            System.out.println("received msg from " + remoteAddress.getHostName() + " with msg \"" + byteBuf.toString(StandardCharsets.UTF_8) + "\"");
        }
    }
}
