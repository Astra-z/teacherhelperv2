package com.spm.teacherhelperv2.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
     * shiro 配置类
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean  getShiroFilterFactoryBean(@Qualifier("getSecurityManager")DefaultWebSecurityManager getSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager);
        Map<String,String> map=new LinkedHashMap<>();

        //swager start
        map.put("/swagger-ui.html**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/doc.html","anon");
        //swagger end

        map.put("/v1/dologin","anon");
        map.put("/**","authc");

        shiroFilterFactoryBean.setLoginUrl("/v1/unlogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SessionManager sessionManager(){
        MySessionManager sessionManager= new MySessionManager();
        //设置过期时间
        sessionManager.setGlobalSessionTimeout(1000*60*30);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //去掉shiro登录时url里的JSessionID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getRealm")UserRealm realm,
                                                        @Qualifier("sessionManager")SessionManager sessionManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;

    }

    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }



    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("getSecurityManager")DefaultWebSecurityManager getSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
