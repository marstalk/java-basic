package com.ljc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;


/**
 * curl -XGET http://127.0.0.1:8088/welcome -H 'Content-Type:Application/json' -H 'Auth: bear xjdkfls' -d '{"name":"liujiacheng","age":12}'
 */
public class EHttpServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;

        // Create the event loop groups
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // Create the server bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new HttpServerCodec())
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new HTTPRequestHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("Server started on port " + port);
            channelFuture.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop groups
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class HTTPRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelActive: isOpen=" + ctx.channel().isOpen() + " isActive=" + ctx.channel().isActive() + " isRemoved=" +  ctx.isRemoved());
            super.channelActive(ctx);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
            print(request);

            // Handle the HTTP request
            String responseContent = "Hello, Netty!";
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(responseContent + " received request body: " + request.content().toString(StandardCharsets.UTF_8), CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

            // Write the response back to the client
            ctx.writeAndFlush(response);
        }
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    private static void print(FullHttpRequest request) {
        // url
        System.out.println(request.method() + " " + request.uri() + " " + request.protocolVersion());

        // header
        Iterator<Map.Entry<String, String>> iterator = request.headers().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + " : " + next.getValue());
        }
        System.out.println();

        // content
        ByteBuf content = request.content();
        System.out.println(content.toString(StandardCharsets.UTF_8));
    }
}

