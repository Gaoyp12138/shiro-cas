package com.example.shirodemo.controller;

import com.example.shirodemo.config.UrlConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: Gaoyp
 * @Description:
 * @Date: Created in 下午6:23 18-2-1
 * @Modified By:
 */
@Controller
public class TestController {


    @Autowired
    private UrlConfig urlConfig;

//    @GetMapping("/login")
//    public String toLogin() {
//        return "login";
//    }

//    @PostMapping("/login")
//    public String login(String username,String password){
//
//        System.out.println("金咖色哥卡斯感到恐惧啊股东卡" + username + "------" + password);
//
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
//
//        usernamePasswordToken.setRememberMe(false);
//
//        subject.login(usernamePasswordToken);
//        return "login";
//    }

    @GetMapping(value = {"/","/index"})
    public @ResponseBody String index(){
        return "index";
    }

    @GetMapping("/error")
    public @ResponseBody String error(){
        return "error";
    }


    @GetMapping("/admin")
    @RequiresPermissions(value = {"ADMIN","DSDAS"} ,logical = Logical.OR)
    public  @ResponseBody String admin(){
        return "admin";
    }


    @GetMapping("/user")
    @RequiresPermissions(value = "USER")
    public @ResponseBody String user(){
        return "user";
    }


    @GetMapping("/show")
    public @ResponseBody String showUrl(){
        return urlConfig.toString();
    }

}
