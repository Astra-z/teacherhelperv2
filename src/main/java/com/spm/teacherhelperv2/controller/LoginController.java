package com.spm.teacherhelperv2.controller;

import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.manager.ListResult;
import com.spm.teacherhelperv2.service.UserService;
import com.spm.teacherhelperv2.util.MD5Utils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * description: LoginController
 * date: 2020/5/16 11:27
 * author: Zhangjr
 * version: 1.0
 */
@RestController
@RequestMapping("${global.version}")
@Api(tags = "s_user表操作API")
public class LoginController {
    @Autowired
    @Qualifier("UserService")
    UserService userService;
    @RequestMapping("/unauth")
    public Map unuth(){
        HashMap<String ,Object > map=new HashMap<>();
        map.put("msg","没有权限！");
        return map;
    }

    @RequestMapping("/unlogin")
    public Map unLogin(){
        HashMap<String ,Object > map=new HashMap<>();
        map.put("msg","没有登录请先登录！");
        return map;
    }

    @PostMapping("/dologin")
    public Map toLogin(@RequestBody UserDO user, Model model){
        HashMap<String ,Object > map=new HashMap<>();
        Subject subject= SecurityUtils.getSubject();
        String username=user.getUsername().trim();
        String password= MD5Utils.encrypt(username.trim().toLowerCase(),user.getPassword());
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            map.put("msg","用户名不存在");
            return map;
        }
        catch (IncorrectCredentialsException e){
            map.put("msg","密码错误");
            return map;
        }
        UserDO userDO=userService.getUserByOther(user.getUsername(),"username");
        map.put("msg","登录成功");
        map.put("status",200);
        map.put("user",userDO);
        map.put("Authorization",subject.getSession().getId());

        return map;
    }
}
