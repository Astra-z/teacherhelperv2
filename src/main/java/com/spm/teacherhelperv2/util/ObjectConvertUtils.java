package com.spm.teacherhelperv2.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: ObjectConvertUtils
 * date: 2020/6/13 10:04
 * author: Zhangjr
 * version: 1.0
 */
public class ObjectConvertUtils {
    private ObjectConvertUtils(){};
    private final static ObjectMapper objectMapper=new ObjectMapper();
    private final static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static <T> T convertValue(Object value,Class<T> clazz) throws Exception {
        if(clazz== Date.class&&value!=null&&value.toString().length()<=19){
            return (T) simpleDateFormat.parse(value.toString());
        }
        else {
            return objectMapper.convertValue(value,clazz);
        }
    }
}
