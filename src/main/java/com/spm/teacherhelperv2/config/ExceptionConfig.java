package com.spm.teacherhelperv2.config;

import com.spm.teacherhelperv2.exception.MyExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: ExceptionConfig
 * date: 2020/5/17 14:03
 * author: Zhangjr
 * version: 1.0
 */
@Configuration
public class ExceptionConfig {

    @Bean
    public MyExceptionResolver myExceptionResolver() {
        return new MyExceptionResolver();
    }
}
