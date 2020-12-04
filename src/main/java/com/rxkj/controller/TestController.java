package com.rxkj.controller;

import com.rxkj.handler.AlexForDTUHandler;
import com.rxkj.util.AlexUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/alex")
    public String helloAlex(){
        return "hello alex!";
    }

    @RequestMapping("/onoroff")
    public Map<String,Object> onoroff(@RequestBody Map mapx){
        String id = (String) mapx.get("id");
        String zhiling = (String) mapx.get("zhiling");
        Map<String,Object> map = new HashMap<>();
        System.out.println("id: " + id);
        System.out.println("zhiling: " + zhiling);
        if ("alex01".equals(id)){
            ChannelHandlerContext ctx = AlexForDTUHandler.ctxMap.get(id);
            ctx.write(AlexUtil.hexStringToByteArray(zhiling));
            ctx.flush();
            map.put("res",1);
        }else {
            map.put("res",0);
        }
        return map;
    }

    @RequestMapping("/pcforcontrl")
    public Map<String,Object> pcforcontrl(@RequestParam("id") String id, @RequestParam("zhiling") String zhiling){
        Map<String,Object> map = new HashMap<>();
        System.out.println("id: " + id);
        System.out.println("zhiling: " + zhiling);
        if ("alex01".equals(id)){
            ChannelHandlerContext ctx = AlexForDTUHandler.ctxMap.get(id);
            ctx.write(AlexUtil.hexStringToByteArray(zhiling));
            ctx.flush();
            map.put("res",1);
        }else {
            map.put("res",0);
        }
        return map;
    }
}
