package com.spm.teacherhelperv2.util;

import ch.qos.logback.core.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * description: MyFileUtils
 * date: 2020/6/7 15:01
 * author: Zhangjr
 * version: 1.0
 */
public class MyFileUtils {
    private MyFileUtils(){};
    public final static String COURSE_HOME_WORK_PATH ="D:\\workspace\\teacherhelperv2_files\\courses\\";
    public final static String STUDENT_FILE_PATH="D:\\workspace\\teacherhelperv2_files\\studentfiles\\";

    public static List<String> findAllMyFileNames(File dir){
        List<String> dirNameList=new ArrayList<>();
        if(!dir.exists()||!dir.isDirectory()){
            return null;
        }
        for(File file:dir.listFiles()) {
            if(file.isFile()){
                dirNameList.add(file.getName());
            }
        }
        return dirNameList;
    }

    public static List<String> findAllFileNames(File dir,String path,List<String> dirNameList){
        if(!dir.exists()||!dir.isDirectory()){
            return null;
        }
        for(File file:dir.listFiles()) {
            if(file.isFile()){
                if(path!=""){
                    dirNameList.add(path+"-"+file.getName());
                }
            }
            if(file.isDirectory()){
                findAllFileNames(file,file.getName(),dirNameList);
            }
        }
        return dirNameList;
    }
}
