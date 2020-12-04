package com.rxkj.handler;

import com.rxkj.util.CharacterConvert;
import com.rxkj.util.ProtoInstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * netty客户端登录处理
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
@Service("loginResponseHandler")
@ChannelHandler.Sharable
public class LoginResponseHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(LoginResponseHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (null == msg || !(msg instanceof byte[])) {
            super.channelRead(ctx, msg);
            return;
        }
        //对接收到的数据进行处理
        byte[] data = (byte[]) msg;
        int dataLength = data.length;
        ByteBuf buf = Unpooled.buffer(dataLength);
        buf.writeBytes(data);
        int type = CharacterConvert.byteToInt(buf.readByte());
        //机器编码
        int deviceId = CharacterConvert.byteToInt(buf.readByte());
        //校验位
        int verify = CharacterConvert.byteToInt(buf.readByte());
        //如果是登录操作时
        if(type == ProtoInstant.LOGIN){
            //计算字头 + 数据长度 + 类型 + 参数的总和
            int sum = CharacterConvert.sum(ProtoInstant.FIELD_HEAD, dataLength + ProtoInstant.FILED_LEN, type, deviceId);
            if(verify != CharacterConvert.getLow8(sum)){
                log.error("登录返回，校验位错误！");
            }else{
                ChannelPipeline channelPipeline = ctx.pipeline();
                channelPipeline.addAfter("encoder", "heartbeat", new HeartBeatHandler());
                // 移除登录响应处理器
                channelPipeline.remove(this);
                log.info("服务器机登录返回了！");
            }
            return;
        }else{
            super.channelRead(ctx, msg);
            return;
        }
    }
}
