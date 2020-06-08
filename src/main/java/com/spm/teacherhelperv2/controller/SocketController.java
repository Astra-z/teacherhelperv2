package com.spm.teacherhelperv2.controller;

import com.spm.teacherhelperv2.config.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * description: SocketController
 * date: 2020/6/8 14:36
 * author: Zhangjr
 * version: 1.0
 */
@Controller
@RequestMapping("${global.version}")
public class SocketController {
    @Autowired
    private WebSocketServer webSocket;

    @GetMapping("/webSocketSend")
    @ResponseBody
    public void socket(@RequestParam("userId")String userId) throws InterruptedException {
        for (int i = 0; i < 10; i++){
            Thread.sleep(1000);
            webSocket.sendInfo(userId, ""+i);
        }
    }
}
