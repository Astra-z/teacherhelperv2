package com.spm.teacherhelperv2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * description: DelayedConfig
 * date: 2020/6/8 13:14
 * author: Zhangjr
 * version: 1.0
 */
@Configuration
public class DelayedConfig {
    protected final static String QUEUE_NAME = "delayed.live.queue";
    protected final static String EXCHANGE_NAME = "delayed.live.exchange";

    @Bean
    public Queue queue() {
        return new Queue(DelayedConfig.QUEUE_NAME);
    }

    /**
     *  配置默认的交换机
     * @return CustomExchange
     */
    @Bean
    CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(DelayedConfig.EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    /**
     * 绑定队列到交换器
     * @param queue 队列
     * @param exchange 交换机
     * @return Binding
     */
    @Bean
    Binding binding(Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DelayedConfig.QUEUE_NAME).noargs();
    }
}
