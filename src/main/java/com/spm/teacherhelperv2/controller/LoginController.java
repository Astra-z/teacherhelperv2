package com.spm.teacherhelperv2.controller;

import com.spm.teacherhelperv2.config.WebSocketServer;
import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.manager.RespondResult;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private WebSocketServer webSocket;

    @RequestMapping("/unauth")
    public RespondResult unuth(){
        return RespondResult.error("没有权限！");
    }

    @RequestMapping("/unlogin")
    public RespondResult unLogin(){
        return RespondResult.error("没有登录请先登录!");
    }

    @PostMapping("/dologin")
    public RespondResult toLogin(@RequestBody UserDO user, Model model){
        HashMap<String ,Object > map=new HashMap<>();
        Subject subject= SecurityUtils.getSubject();
        String username=user.getUsername().trim();
        String password= MD5Utils.encrypt(username.trim().toLowerCase(),user.getPassword());
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            return RespondResult.error("用户名不存在");
        }
        catch (IncorrectCredentialsException e){
            return RespondResult.error("密码错误");
        }
        UserDO userDO=userService.getUserByOther(user.getUsername(),"username");
        map.put("user",userDO);
        String sessionid= (String) subject.getSession().getId();
        map.put("Authorization",subject.getSession().getId());
        return RespondResult.success("登录成功",map);
    }

    @GetMapping("Loginout")
    @ResponseBody
    public void loginout(@RequestParam("userId")String userId){

    }
}
