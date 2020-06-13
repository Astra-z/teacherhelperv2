package com.spm.teacherhelperv2.config;


import com.spm.teacherhelperv2.dao.NoteMapper;
import com.spm.teacherhelperv2.entity.NoteDO;
import com.spm.teacherhelperv2.entity.UserNoteDO;
import com.spm.teacherhelperv2.service.NoteService;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * description: DelayedReceiver
 * date: 2020/6/8 13:15
 * author: Zhangjr
 * version: 1.0
 */
@SuppressWarnings("ALL")
@Component
@RabbitListener(queues = DelayedConfig.QUEUE_NAME)
public class DelayedReceiver {
    private WebSocketServer webSocketServer;
    private NoteMapper noteMapper;
    private DelayedSender delayedSender;
    private Logger logger = LoggerFactory.getLogger(DelayedReceiver.class);

    @Autowired
    public DelayedReceiver(WebSocketServer webSocketServer,NoteMapper noteMapper,DelayedSender delayedSender){
        this.noteMapper=noteMapper;
        this.webSocketServer=webSocketServer;
        this.delayedSender=delayedSender;
    }

    @RabbitHandler
    public void process(byte[] body) {
        NoteDO noteDO= SerializationUtils.deserialize(body);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("接收时间:" + sdf.format(new Date()));
        logger.info("消息内容：" + noteDO);
        //如果用户在线直接发送消息
        //统一后面的数据格式，都用list发送
        List<NoteDO> list=new ArrayList<>();
        list.add(noteDO);
        //接受到消息以后先判断是否该任务已被取消
        NoteDO newNote=noteMapper.selectById(noteDO.getNoteId());
        if(newNote.getNoteSwitch()){
            if(newNote.getNoteType()==2){
                newNote.setNoteSwitch(false);
                noteMapper.updateById(newNote);
            }
            else{
                Date date=newNote.getEndTime();
                Calendar calendar= Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 7);
                newNote.setEndTime(calendar.getTime());
                noteMapper.updateById(newNote);
                delayedSender.send(newNote,calendar.getTime().getTime()-(System.currentTimeMillis()));
            }
            Boolean flag=webSocketServer.sendObjMessage(noteDO.getUserId().toString(),list);
            //如果发送失败说明用户不在线则存到本地的缓存hashmap userNoteList中
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
}
