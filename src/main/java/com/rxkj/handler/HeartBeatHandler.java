package com.rxkj.handler;

import com.rxkj.util.CharacterConvert;
import com.rxkj.util.ProtoInstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * netty客户端心跳处理
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
@ChannelHandler.Sharable
@Service("heartHandler")
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(HeartBeatHandler.class);

    // 心跳的时间间隔，单位为s
    private static final int HEARTBEAT_INTERVAL = 100;

    // 在Handler被加入到Pipeline时，开始发送心跳
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.buffer(3);
        //心跳
        buf.writeByte(ProtoInstant.HEART_BEAT);
        //机器编码
        buf.writeByte(ProtoInstant.DEVICE_ID);
        //校验位
        int sum = CharacterConvert.sum(ProtoInstant.FIELD_HEAD, 6, ProtoInstant.HEART_BEAT, ProtoInstant.DEVICE_ID);
        int verify = CharacterConvert.getLow8(sum);
        buf.writeByte(verify);
        // 发送心跳
        heartBeat(ctx, buf.array());
    }

    // 使用定时器，发送心跳报文
    public void heartBeat(ChannelHandlerContext ctx, byte[] heartbeatMsg) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                log.info(" 发送心跳 消息 to netty服务器系统");
                ctx.writeAndFlush(heartbeatMsg);
                // 递归调用，发送下一次的心跳
                heartBeat(ctx, heartbeatMsg);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 接受到服务器的心跳回写
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 判断消息实例
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
        int deviceId = CharacterConvert.byteToInt(buf.readByte());
        //如果是心跳信息时
        if(type == ProtoInstant.HEART_BEAT){
            int verify = CharacterConvert.byteToInt(buf.readByte());
            //计算字头 + 数据长度 + 类型 + 参数的总和
            int sum = CharacterConvert.sum(ProtoInstant.FIELD_HEAD, dataLength + ProtoInstant.FILED_LEN, type, deviceId);
            if(verify != CharacterConvert.getLow8(sum)){
                log.error("心跳包，校验位错误！");
            }else{
                log.info("收到回写的心跳 消息 from netty服务器系统");
            }
            return;
        }else{
            super.channelRead(ctx, msg);
        }
    }
}
