package com.ljc.bytebuf;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class ByteBufUtilDemo {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("hello", Charset.forName("utf8"));
        

        System.out.println(ByteBufUtil.hexDump(buf));
        System.out.println(ByteBufUtil.prettyHexDump(buf));

        
    }
}
