package com.spm.teacherhelperv2.entity;

import org.apache.tomcat.jni.User;

import javax.persistence.Transient;
import java.util.List;

/**
 * description: UserDTO
 * date: 2020/5/17 20:55
 * author: Zhangjr
 * version: 1.0
 */
public class UserDTO extends User {
    @Transient
    private List<String> roleName;

    public List<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }
}
