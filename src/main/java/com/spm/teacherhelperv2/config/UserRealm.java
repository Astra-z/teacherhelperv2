package com.spm.teacherhelperv2.config;

import com.spm.teacherhelperv2.dao.UserMapper;
import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.service.UserService;
import org.apache.catalina.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * description: Realm
 * date: 2020/5/16 11:36
 * author: Zhangjr
 * version: 1.0
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Qualifier("UserService")
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        System.out.println(username);
        UserDO user=userService.getUserByOther(username,"username");
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"");
    }
}
