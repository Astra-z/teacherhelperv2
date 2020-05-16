package com.spm.teacherhelperv2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
public class Teacherhelperv2Application {

    public static void main(String[] args) {
        SpringApplication.run(Teacherhelperv2Application.class, args);
    }

}
