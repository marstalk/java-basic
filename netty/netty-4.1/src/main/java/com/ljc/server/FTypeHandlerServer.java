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

import java.nio.charset.StandardCharsets;

public class FTypeHandlerServer {
    public static void main(String[] args) {
        int port = 9999;

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new TypeHandler());
                            channel.pipeline().addLast(new SimplePrintHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    /**
     * 只有消息类型是MyMsg类型，才会进入这个handler。
     */
    static class TypeHandler extends SimpleChannelInboundHandler<MyMsg>{

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMsg myMsg) throws Exception {
            System.out.println(myMsg.toString());
        }
    }

    static class SimplePrintHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
            System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
            ctx.fireChannelRead(byteBuf);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println(cause.getCause().getMessage());
        }
    }

    static class MyMsg{
        int id;
        String name;

        @Override
        public String toString() {
            return "MyMsg{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
