package com.rxkj.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * netty dtu服务端数据接收处理handler
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
public class AlexForDTUHandler extends SimpleChannelInboundHandler<FullHttpRequest> { // 1

    private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    private static Logger log = LoggerFactory.getLogger(AlexForDTUHandler.class);

    //用于存放通信管道链接对象
    public static Map<String, ChannelHandlerContext> ctxMap = new ConcurrentHashMap<String, ChannelHandlerContext>(16);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        System.out.println("class:" + msg.getClass().getName());
        /*DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer("test".getBytes())); // 2

        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 3
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        ctx.write(response);*/
    }

    /**
     * 在与客户端的连接已经建立之后将被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端与客户端的通信连接已建立...");
        removeChannelMap(ctx);
        super.channelInactive(ctx);
    }

    /**
     * 客户端主动断开服务端的连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        removeChannelMap(ctx);
        super.channelInactive(ctx);
    }


    /**
     * 当从客户端接收到一个消息时被调用
     * msg 就是硬件传送过来的数据信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 将该设备的通信管道对象保存
        System.out.println("msg: " + ((String) msg));
        if (((String) msg).indexOf("alex") >= 0){
            String id = (String) msg;
            //发送的是心跳信息，记录该设备id及通信管道
            if (!(ctxMap.containsKey(id))){
                ctxMap.put(id,ctx);
            }
        }
        //后续业务处理
    }


    /**
     * 当该设备（客户端）断开连接时，调用移除已失效的通信管道对象
     * @param ctx
     */
    private void removeChannelMap(ChannelHandlerContext ctx) {
        for( String key :ctxMap.keySet()){
            if(ctxMap.get(key)!=null && ctxMap.get(key).equals(ctx)){
                ctxMap.remove(key);
            }
        }
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通信管道接收完数据了...");
        ctx.flush();
        /*super.channelReadComplete(ctx);
        ctx.flush(); // 4*/
        //ctx.writeAndFlush(AlexUtil.hexStringToByteArray("01050000FF008C3A"));
    }

    /**
     * 异常发生时调用的方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("捕获异常信息。。。");
        if(null != cause) cause.printStackTrace();
        if(null != ctx) ctx.close();
    }
}
