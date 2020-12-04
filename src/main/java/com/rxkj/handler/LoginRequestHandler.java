package com.rxkj.handler;

import com.rxkj.util.CharacterConvert;
import com.rxkj.util.ProtoInstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 管理后台登录处理
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
@Service("loginRequestHandler")
@ChannelHandler.Sharable
public class LoginRequestHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(LoginRequestHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (null == msg || !(msg instanceof byte[])) {
            super.channelRead(ctx, msg);
            return;
        }

        byte[] data = (byte[]) msg;
        int dataLength = data.length;
        ByteBuf buf = Unpooled.buffer(dataLength);
        buf.writeBytes(data);
        int type = CharacterConvert.byteToInt(buf.readByte());
        //机器编号
        int deviceId = CharacterConvert.byteToInt(buf.readByte());
        //如果是管理后台登录操作时
        if(type == ProtoInstant.LOGIN){
            log.info("客户端登录了，机器编码：" + deviceId);
            int verify = CharacterConvert.byteToInt(buf.readByte());
            //加上字头，数据长度（数据长度包括字头和数据长度本身的位数）
            int sum = CharacterConvert.sum(ProtoInstant.FIELD_HEAD, dataLength + ProtoInstant.FILED_LEN, type, deviceId);
            if(verify != CharacterConvert.getLow8(sum)){
                log.error("客户端，校验位错误，机器编码：" + deviceId);
                //验证错误，关闭链接
                //ctx.close();
            }else{
                log.info("客户端登录成功处理");
                buf.clear();
                buf.writeByte(ProtoInstant.LOGIN);
                buf.writeByte(deviceId);
                buf.writeByte(verify);
                //回写消息
                ctx.channel().writeAndFlush(buf.array());
            }
        }else{
            super.channelRead(ctx, msg);
        }
    }
}
