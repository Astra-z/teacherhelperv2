package com.spm.teacherhelperv2.config;


import com.spm.teacherhelperv2.entity.NoteDO;
import com.spm.teacherhelperv2.entity.UserNoteDO;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description: DelayedReceiver
 * date: 2020/6/8 13:15
 * author: Zhangjr
 * version: 1.0
 */
@Component
@RabbitListener(queues = DelayedConfig.QUEUE_NAME)
public class DelayedReceiver {
    @Autowired
    WebSocketServer webSocketServer;
    private Logger logger = LoggerFactory.getLogger(DelayedReceiver.class);
    @RabbitHandler
    public void process(byte[] body) {
        NoteDO noteDO= (NoteDO)SerializationUtils.deserialize(body);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("接收时间:" + sdf.format(new Date()));
        logger.info("消息内容：" + noteDO);
        //如果用户在线直接发送消息
        Boolean flag=webSocketServer.sendObjMessage(noteDO.getUserId().toString(),noteDO);
        //如果发送失败说明用户不在线则存到本地的hashmap userNoteList中
        if(!flag){
            if(UserNoteDO.userNoteList.containsKey(noteDO.getUserId())){
                UserNoteDO.userNoteList.get(noteDO.getUserId()).add(noteDO);
            }else{
                List<NoteDO> userNoteList=new ArrayList<>();
                userNoteList.add(noteDO);
                UserNoteDO.userNoteList.put(noteDO.getUserId(),userNoteList);
            }
        }
    }
}
