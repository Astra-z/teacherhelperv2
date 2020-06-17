package com.spm.teacherhelperv2.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;

/**
 * description: MySessionManager
 * date: 2020/5/17 14:46
 * author: Zhangjr
 * version: 1.0
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";



    /**
     * 自定义session管理
     * <br/>
     * 传统结构项目中，shiro从cookie中读取sessionId以此来维持会话，在前后端分离的项目中（也可在移动APP项目使用），
     * 我们选择在ajax的请求头中传递sessionId，因此需要重写shiro获取sessionId的方式。
     * 自定义MySessionManager类继承DefaultWebSessionManager类，重写getSessionId方法
     **/
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String sessionId = httpServletRequest.getHeader(AUTHORIZATION);
        //如果请求头中含有AUTHORIZATION，则设置为sessionId
        if(!StringUtils.isEmpty(sessionId)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }else {
            //否则按默认规则从cookie取sessionId
            return "zdy"+ UUID.randomUUID().toString();
        }

    }
}
