package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.bean.user.User;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("shiro")
@Controller
public class ShiroController {

    private static final Logger c_logger = Logger.getLogger(ShiroController.class);

    @RequestMapping(value = "login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object login(@RequestParam("username")String username,
                        @RequestParam("password")String password)
    {
        Map map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        try {
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                token.setRememberMe(true);
                subject.login(token);
            }
            User user = ((User) subject.getSession().getAttribute("user"));
            map.put("code",0);
            map.put("msg","");
            map.put("data",user);
            map.put("count",0);
            return map;
        }catch (Exception e)
        {
            c_logger.error("Exception error",e);
            map.put("code",0);
            map.put("msg","");
            map.put("data",1);
            map.put("count",0);
            return map;
        }

    }
}
