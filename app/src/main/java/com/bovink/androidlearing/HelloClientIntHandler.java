package com.bovink.androidlearing;

import com.google.gson.Gson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by fox on 2017/7/12.
 */

public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
    ChannelHandlerContext ctx;
    User user;
    Gson gson = new Gson();

    public HelloClientIntHandler(User user) {
        this.user = user;
    }

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HelloClientIntHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1));
        result.release();
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        System.out.println("HelloClientIntHandler.channelActive");
        char[] toSend = gson.fromJson(gson.toJson(user), char[].class);
        ByteBuf response = Unpooled.copiedBuffer(toSend,
                CharsetUtil.UTF_8);

        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public void sendMessage(char[] json) {

    }
}
