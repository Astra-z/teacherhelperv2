package com.spm.teacherhelperv2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * description: ioFiletest
 * date: 2020/6/7 9:53
 * author: Zhangjr
 * version: 1.0
 */
public class ioFiletest {
    public static void main(String[] args) {
        String Filepath="F:\\IJ\\teacherhelperv2\\courses\\1\\1\\zhangsan\\";
        File dir =new File(Filepath);
        List<String> dirNameList=new ArrayList<>();
        findAllFiles(dir,dirNameList);
        System.out.println(dirNameList);
    }
    static void findAllFiles(File dir, List<String> dirNameList){
        if(!dir.exists()||!dir.isDirectory())
            return;
        for(File file:dir.listFiles()) {
            if(file.isFile()){
                dirNameList.add(file.getPath());
            }
        }
    }
}
