package com.spm.teacherhelperv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.teacherhelperv2.entity.NoteDO;
import com.spm.teacherhelperv2.util.ObjectConvertUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description: dateTest
 * date: 2020/6/8 17:04
 * author: Zhangjr
 * version: 1.0
 */
public class dateTest {
    private static final ObjectMapper objectMapper=new ObjectMapper();
    public static void main(String[] args) throws ParseException {
//        NoteDO noteDO=new NoteDO();
//        noteDO.setCreateTime(new Date());
//        System.out.println(noteDO.getCreateTime());
//        noteDO.setModifyTime(new Date());
//        System.out.println(noteDO.getModifyTime().getTime()-noteDO.getCreateTime().getTime());
//
//        Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse("2006-06-08");
//        Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse("2006-06-12");
//        long l = date1.getTime()-date2.getTime()>0 ? date1.getTime()-date2.getTime():
//                date2.getTime()-date1.getTime();
//        System.out.println(date1);
//        System.out.println(date1.getTime());
//        System.out.println(l/1000+"秒");

        Date date=new Date();
        Calendar calendar  =   Calendar.getInstance();
        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(calendar.DATE, 7);//把日期往后增加n天.正数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        System.out.println(date);


    }
}
