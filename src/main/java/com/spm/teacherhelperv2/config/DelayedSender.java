package com.spm.teacherhelperv2.config;

import com.spm.teacherhelperv2.entity.NoteDO;
import net.sf.ehcache.search.expression.Not;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: DelaySender
 * date: 2020/6/8 13:17
 * author: Zhangjr
 * version: 1.0
 */
@Component
public class DelayedSender {
    private Logger log = LoggerFactory.getLogger(DelayedReceiver.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(NoteDO noteDO, long delayMillSeconds) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("发送时间：" + sf.format(new Date()));
        rabbitTemplate.convertAndSend(DelayedConfig.EXCHANGE_NAME, DelayedConfig.QUEUE_NAME, SerializationUtils.serialize(noteDO), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", delayMillSeconds);
                return message;
            }
        });
    }
}
