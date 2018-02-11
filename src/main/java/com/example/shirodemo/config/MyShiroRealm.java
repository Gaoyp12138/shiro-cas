package com.example.shirodemo.config;

import com.example.shirodemo.domin.User;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @Author: Gaoyp
 * @Description:
 * @Date: Created in 下午5:46 18-2-1
 * @Modified By:
 */
public class MyShiroRealm extends CasRealm{


    @Autowired
    private UserService userService;

    @Autowired
    private UrlConfig urlConfig;


    @PostConstruct
    public void initProperty(){

//        String url = UrlConfig.getCasServerUrlPrefix();

//        setCasServerUrlPrefix("https://cas.example.org:8443/cas");
        setCasServerUrlPrefix(urlConfig.getCasServerUrlPrefix());

        //客户端回调地址
//        setCasService("http://localhost:9090/shiro-cas");
        setCasService(urlConfig.getShiroServerUrlPrefix() + urlConfig.getCasFilterUrlPattern());
    }


    //为了得到用户权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username  = (String)principalCollection.getPrimaryPrincipal();

        if (username != null){

            User user = userService.getUserByUsername(username);

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermission(user.getAuthorityInfo());


            return simpleAuthorizationInfo;
        }else {
            return null;
        }

    }

//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        if (authenticationToken.getPrincipal() == null){
//            return null;
//        }
//
//        String username = authenticationToken.getPrincipal().toString();
//
//        User user = userService.getUserByUsername(username);
//
//        if (user == null){
//            return null;
//        }else {
//            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),getName());
//
//            return simpleAuthenticationInfo;
//        }
//
//    }
}
