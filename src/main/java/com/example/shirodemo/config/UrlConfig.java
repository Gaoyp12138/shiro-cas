package com.example.shirodemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Gaoyp
 * @Description:  相关url
 * @Date: Created in 上午11:21 18-2-8
 * @Modified By:
 */
@Component
public class UrlConfig {





    private static String casServerUrlPrefix;
    private static String casLoginUrl;
    private static String casLogoutUrl;
    private static String shiroServerUrlPrefix;
    private static String casFilterUrlPattern;
    private static String loginUrl;
    private static String logoutUrl;


    public static String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public static String getCasLoginUrl() {
        return casLoginUrl;
    }

    public static String getCasLogoutUrl() {
        return casLogoutUrl;
    }

    public static String getShiroServerUrlPrefix() {
        return shiroServerUrlPrefix;
    }

    public static String getCasFilterUrlPattern() {
        return casFilterUrlPattern;
    }

    public static String getLoginUrl() {
        return loginUrl;
    }

    public static String getLogoutUrl() {
        return logoutUrl;
    }


    @Value("${cas.server.url}")
    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        UrlConfig.casServerUrlPrefix = casServerUrlPrefix;
    }

    @Value("${cas.login.url}")
    public void setCasLoginUrl(String casLoginUrl) {
        UrlConfig.casLoginUrl = casLoginUrl;
    }

    @Value("${cas.logout.url}")
    public void setCasLogoutUrl(String casLogoutUrl) {
        UrlConfig.casLogoutUrl = casLogoutUrl;
    }

    @Value("${app.server.url}")
    public void setShiroServerUrlPrefix(String shiroServerUrlPrefix) {
        UrlConfig.shiroServerUrlPrefix = shiroServerUrlPrefix;
    }

    @Value("${cas.filter.url.pattern}")
    public void setCasFilterUrlPattern(String casFilterUrlPattern) {
        UrlConfig.casFilterUrlPattern = casFilterUrlPattern;
    }

    @Value("${app.login.url}")
    public void setLoginUrl(String loginUrl) {
        UrlConfig.loginUrl = loginUrl;
    }

    @Value("${app.logout.url}")
    public void setLogoutUrl(String logoutUrl) {
        UrlConfig.logoutUrl = logoutUrl;
    }
}
