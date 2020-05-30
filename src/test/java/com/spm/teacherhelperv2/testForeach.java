package com.spm.teacherhelperv2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.teacherhelperv2.entity.MenuDO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: testForeach
 * date: 2020/5/18 12:03
 * author: Zhangjr
 * version: 1.0
 */
public class testForeach {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MenuDO menuDO=new MenuDO();
        Method method= MenuDO.class.getMethod("setParentId", Long.class);
        Class typeClass=Long.class;
        Integer i=5;
        ObjectMapper objectMapper=new ObjectMapper();
        method.invoke(menuDO,new Object[]{objectMapper.convertValue(i,typeClass)});



        System.out.println(menuDO.getParentId());
    }
}
