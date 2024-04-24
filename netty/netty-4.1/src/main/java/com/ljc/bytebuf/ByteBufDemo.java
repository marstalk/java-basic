package com.ljc.bytebuf;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * ByteBufDemo
 */
public class ByteBufDemo {

    public static void main(String[] args) {
        Charset utf8 = Charset.forName("utf-8");
        ByteBuf buf =  Unpooled.copiedBuffer("Hello Netty Action Rocks", utf8);

        // 验证切片底层是相同的字节数组
        ByteBuf sliced = buf.slice(0, 11);
        sliced.setByte(0, 'h');
        System.out.println(sliced.toString(utf8));
        System.out.println(sliced.getByte(0) == buf.getByte(0));

        // 验证copy的底层【不是】相同的字节数组
        ByteBuf copied = buf.copy(0, 5);
        copied.setByte(0, 'a');
        System.out.println(copied.toString(utf8));
        System.out.println(copied.getByte(0) != buf.getByte(0));

        // 中文
        ByteBuf chinese = Unpooled.copiedBuffer("你好", Charset.forName("GBK"));
        System.out.println(chinese.writerIndex());


    }
}