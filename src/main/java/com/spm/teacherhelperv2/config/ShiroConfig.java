package com.spm.teacherhelperv2.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description: ShiroConfig
 * date: 2020/5/16 11:36
 * author: Zhangjr
 * version: 1.0
 */
@Configuration
public class ShiroConfig {
    /**
     * shiro Bean工厂
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean  getShiroFilterFactoryBean(@Qualifier("getSecurityManager")DefaultWebSecurityManager getSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager);
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/v1/users/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getRealm")UserRealm realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;

    }

    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }

}
