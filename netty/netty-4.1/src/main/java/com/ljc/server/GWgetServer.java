package com.ljc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GWgetServer {
    public static void main(String[] args) {
        int port = 9999;

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel channel) throws Exception {
                    //channel.pipeline().addLast(new SimplePrintHandler());
                    channel.pipeline().addLast(new HttpServerCodec()); // Codec既能处理HTTP请求也可以处理HTTP响应。
                    channel.pipeline().addLast(new HttpObjectAggregator(1024 * 1024 * 20)); //
                    channel.pipeline().addLast(new DownloadHandler());
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

    static class DownloadHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
            String uri = request.uri();
            String fileUrl = "netty/netty-4.1/data" + uri;
            Path filePath = Paths.get(fileUrl);
            if (!filePath.toFile().exists()) {
                ctx.channel().writeAndFlush("file doesn't exist");
                HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND, Unpooled.copiedBuffer("Error", StandardCharsets.UTF_8));
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                return;
            }

            File file = new File(fileUrl);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

            HttpUtil.setContentLength(response, file.length());
            HttpHeaders.setHeader(response, HttpHeaderNames.CONTENT_TYPE, "application/octet-stream");
            HttpHeaders.setHeader(response, HttpHeaderNames.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
            if(HttpHeaders.isKeepAlive(request)){
                HttpHeaders.setHeader(response, HttpHeaderNames.KEEP_ALIVE, "true");
            }

            ctx.write(response);
            ChannelFuture sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, file.length(), 8192), ctx.newProgressivePromise());
            sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
                @Override
                public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
                    if (total <0){
                        System.out.println("Transfer progress: " + progress);
                    }else{
                        System.out.println("Transfer progress: " + progress + " / " + total);
                    }
                }

                @Override
                public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                    System.out.println("File transfer complete");

                }
            });

            ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if(!HttpHeaders.isKeepAlive(request)){
                lastContentFuture.addListener(ChannelFutureListener.CLOSE);
            }
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
}
