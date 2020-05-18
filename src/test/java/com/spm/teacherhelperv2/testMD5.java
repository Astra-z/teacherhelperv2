package com.spm.teacherhelperv2;

import com.spm.teacherhelperv2.util.MD5Utils;

/**
 * description: testMD5
 * date: 2020/5/16 18:59
 * author: Zhangjr
 * version: 1.0
 */
public class testMD5 {
    public static void main(String[] args) {
        System.out.println(MD5Utils.encrypt("zhangsan","123"));
        System.out.println(MD5Utils.encrypt("lisi", "123"));

    }
}
