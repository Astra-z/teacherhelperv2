package com.spm.teacherhelperv2;

import com.spm.teacherhelperv2.entity.NoteDO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: dateTest
 * date: 2020/6/8 17:04
 * author: Zhangjr
 * version: 1.0
 */
public class dateTest {
    public static void main(String[] args) throws ParseException {
        NoteDO noteDO=new NoteDO();
        noteDO.setCreateTime(new Date());
        System.out.println(noteDO.getCreateTime());
        noteDO.setModifyTime(new Date());
        System.out.println(noteDO.getModifyTime().getTime()-noteDO.getCreateTime().getTime());

        Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse("2006-06-08");
        Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse("2006-06-12");
        long l = date1.getTime()-date2.getTime()>0 ? date1.getTime()-date2.getTime():
                date2.getTime()-date1.getTime();
        System.out.println(date1);
        System.out.println(date1.getTime());
        System.out.println(l/1000+"秒");
    }
}
