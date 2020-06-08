package com.spm.teacherhelperv2.config;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: WebSocketServer
 * date: 2020/6/8 14:34
 * author: Zhangjr
 * version: 1.0
 */
@ServerEndpoint("/webSocket/{userId}")
@Component
public class WebSocketServer {
    private Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId")String userId) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(userId, session);
        System.out.println("【websocket消息】有新的连接，总数为:"+webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("【websocket消息】连接断开，总数为:"+webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:"+message);
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for(WebSocketServer webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送文本)
    public void sendTextMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送对象)
    public Boolean sendObjMessage(String userId, Object message) {
        Session session = sessionPool.get(userId);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(JSON.toJSONString(message));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
