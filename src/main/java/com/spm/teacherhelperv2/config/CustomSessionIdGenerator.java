package com.spm.teacherhelperv2.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * description: CustomSessionIdGenerator
 * date: 2020/6/15 18:47
 * author: Zhangjr
 * version: 1.0
 */
public class CustomSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "zdy"+UUID.randomUUID().toString();
    }
}
