package com.spm.teacherhelperv2.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: UserNoteDO
 * date: 2020/6/8 16:36
 * author: Zhangjr
 * version: 1.0
 */
public class UserNoteDO {
    private UserNoteDO(){}

    public static final Map<Integer, List<NoteDO>>userNoteList=new ConcurrentHashMap<>(64);

}
