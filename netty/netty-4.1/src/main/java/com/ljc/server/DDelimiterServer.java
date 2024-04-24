package com.ljc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class DDelimiterServer {
    public static void main(String[] args) {
        int port = 9999;

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap =  new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler((new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 使用【换行符】作为消息的结束，最大长度是20mb
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024*1024*20, Delimiters.lineDelimiter()));
                            socketChannel.pipeline().addLast(new PrintHandler());
                        }
                    }))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);;
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("server started on port " + port);

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class PrintHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            InetSocketAddress remoteAddress = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
            String clientIp = remoteAddress.getAddress().getHostAddress();

            System.out.println("received msg from " + clientIp + " with msg is \"" + byteBuf.toString(StandardCharsets.UTF_8) + "\"");

        }
    }
}
