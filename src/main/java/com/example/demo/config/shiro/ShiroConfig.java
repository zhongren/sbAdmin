package com.example.demo.config.shiro;


import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/auth/login", "anon");

        // logged in users with the 'admin' role
        // chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        // chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/user/page", "authc, perms[user:page]");
        chainDefinition.addPathDefinition("/pic/**", "anon");
        chainDefinition.addPathDefinition("/**", "authc");
        //chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    @Bean
    public Realm realm() {
        ShiroCredentialsMatcher shiroCredentialsMatcher = new ShiroCredentialsMatcher();
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(shiroCredentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public SessionListener sessionListener() {
        return new ShiroSessionListener();
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     */
    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return enterpriseCacheSessionDAO;
    }

    @Bean()
    public SimpleCookie sessionIdCookie() {
        //这个参数是cookie的名称
        ShiroSimpleCookie simpleCookie = new ShiroSimpleCookie("sid");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(false);
        simpleCookie.setPath("/");
        //maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        //配置监听
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);

        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionDAO(sessionDAO());
        //sessionManager.setCacheManager(ehCacheManager());

        //全局会话超时时间（单位毫秒），默认1小时  暂时设置为10秒钟 用来测试
        sessionManager.setGlobalSessionTimeout(3600000);
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        sessionManager.setSessionValidationInterval(3600000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;

    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }


}
