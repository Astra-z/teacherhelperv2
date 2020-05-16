package com.spm.teacherhelperv2.controller;

import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.manager.ListResult;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * description: LoginController
 * date: 2020/5/16 11:27
 * author: Zhangjr
 * version: 1.0
 */
@Controller
//@RequestMapping("${global.version}/")
@Api(tags = "s_user表操作API")
public class LoginController {
    @GetMapping("/dologin")
    public UserDO toLogin(){
        String username="zhangsan";
        String password="124";
        System.out.println("login");
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            System.out.println("用户名查找不到");
        }
        catch (IncorrectCredentialsException e){
            System.out.println("密码不正确");
        }
        UserDO userDO=new UserDO();
        System.out.println("成功");
        return userDO;
    }
}
