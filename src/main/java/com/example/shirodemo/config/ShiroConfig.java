package com.example.shirodemo.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Gaoyp
 * @Description: Shiro配置注入spring的bean，核心是SecurityManger
 * @Date: Created in 下午6:05 18-2-1
 * @Modified By:
 */
@Configuration
public class ShiroConfig {

//    private static final String casFilterUrlPattern = "/shiro-cas";

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm());
        defaultWebSecurityManager.setCacheManager(new MemoryConstrainedCacheManager());
        defaultWebSecurityManager.setSubjectFactory(new CasSubjectFactory());
        return defaultWebSecurityManager;
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle","true");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }



    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String,String>();
        map.put(UrlConfig.getCasFilterUrlPattern(),"casFilter");
        map.put("/**","authc");
        map.put("/logout","logout");
        shiroFilterFactoryBean.setLoginUrl(UrlConfig.getLoginUrl());
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        Map<String,Filter> filterMap = new HashMap<String, Filter>();
        filterMap.put("casFilter",casFilter());
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl(UrlConfig.getLogoutUrl());
        logoutFilter.setEnabled(true);
        filterMap.put("logout",logoutFilter);
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "casFilter")
    public CasFilter casFilter(){
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
//        String loginUrl = "https://cas.example.org:8443/cas/login?service=http://localhost:9090" + casFilterUrlPattern;
        casFilter.setFailureUrl(UrlConfig.getLoginUrl());
//        casFilter.setFailureUrl("/error");
        return casFilter;
    }



    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
