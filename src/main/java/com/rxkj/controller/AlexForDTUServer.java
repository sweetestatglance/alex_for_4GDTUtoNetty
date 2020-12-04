package com.rxkj.controller;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.rxkj.handler.ExceptionHandler;
import com.rxkj.handler.HeartBeatServerHandler;
import com.rxkj.handler.AlexForDTUHandler;
import com.rxkj.handler.LoginRequestHandler;
import com.rxkj.codec.ByteArrayDecoder;
import com.rxkj.codec.ByteArrayEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * netty dtu服务端
 * 用于监听dtu硬件的通信，中心服务端
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
@Component
public class AlexForDTUServer {

    private static Logger log = LoggerFactory.getLogger(AlexForDTUServer.class);

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    //开启两个线程池
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    //启动装饰类
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    //本机的ip地址
    private String ip;

    //启动端口获取
    @Value("${alex.port}")
    private int port;

    @Autowired
    private LoginRequestHandler loginRequestHandler;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 启动服务
     * @param
     */
    public void start(){

        try {
            //获取本机的ip地址
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        serverBootstrap.group(bossGroup,workGroup)
                //非阻塞
                .channel(NioServerSocketChannel.class)
                //连接缓冲池的大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置通道Channel的分配器
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<SocketChannel>(){

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //添加编解码和处理器(节点间通讯用)
                        pipeline.addLast("deCoder", new ByteArrayDecoder())
                                .addLast("enCoder", new ByteArrayEncoder())
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                //数据接收处理器
                                .addLast("alexhandler", new AlexForDTUHandler())
                                //管理后台登录
                                .addLast("loginHandler", loginRequestHandler)
                                //心跳检测
                                .addLast("heartBeat", new HeartBeatServerHandler())
                                //异常处理
                                .addLast("exception", exceptionHandler);

                    }
                });

        ChannelFuture channelFuture = null;
        //启动成功标识
        boolean startFlag = false;
        //启动失败时，多次启动，直到启动成功为止
        while(!startFlag){
            try{
                channelFuture = serverBootstrap.bind(port).sync();
                startFlag = true;
            }catch(Exception e){
                log.info("端口号：" + port + "已被占用！");
                port++;
                log.info("尝试一个新的端口：" + port);
                //重新便规定端口号
                serverBootstrap.localAddress(new InetSocketAddress(port));
            }
        }

        //服务端启动监听事件
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                //启动成功后的处理
                if (future.isSuccess()) {
                    log.info("netty dtu服务器启动成功，Started Successed:" + ip + ":" + port);
                } else {
                    log.info("netty dtu服务器启动失败，Started Failed:" + ip + ":" + port);
                }
            }
        });

        try {
            // 7 监听通道关闭事件
            // 应用程序会一直等待，直到channel关闭
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发生其他异常", e);
        } finally {
            // 8 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
