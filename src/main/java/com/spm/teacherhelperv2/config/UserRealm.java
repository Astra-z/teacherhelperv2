package com.spm.teacherhelperv2.config;

import com.spm.teacherhelperv2.entity.MenuDO;
import com.spm.teacherhelperv2.entity.RoleDO;
import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.service.MenuService;
import com.spm.teacherhelperv2.service.RoleService;
import com.spm.teacherhelperv2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    @Qualifier("RoleService")
    RoleService roleService;

    @Autowired
    @Qualifier("MenuService")
    MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username= (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户角色集合
        List<RoleDO> roleList = this.roleService.findUserRole(username);
        Set<String> roles=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roles);

        //获取角色权限集合
        List<MenuDO> menuList=this.menuService.findPermsByUsername(username);
        Set<String> perms=menuList.stream().map(MenuDO::getPerms).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(perms);
//        System.out.println(perms);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        String password= new String((char[]) authenticationToken.getCredentials());
        UserDO user=userService.getUserByOther(username,"username");
        if(user==null){
            throw new UnknownAccountException("用户名不存在!");
        }
        if(!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("密码错误!");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
