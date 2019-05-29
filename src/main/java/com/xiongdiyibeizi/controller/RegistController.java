package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.bean.user.User;
import com.xiongdiyibeizi.common.Contains;
import com.xiongdiyibeizi.service.api.UserService;
import com.xiongdiyibeizi.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("regist")
public class RegistController {

    private static final Logger c_logger = Logger.getLogger(RegistController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object registUser(@RequestParam(value = "username")String username,
                             @RequestParam(value = "password")String password,
                             @RequestParam(value = "userType")String userType)
    {
        Map map = new HashMap();
        try {
            int ext = userService.getCountUser(username);
            //如果该用户名已经存在，返回1表示存在，0表示不存在
            if (ext == 1){
                map.put("code",0);
                map.put("msg","");
                map.put("data",1);
                map.put("count",0);
                return map;
            }
            String newPassword = StringUtil.INST.getPassword(Contains.MD5, password, username);
            User user = new User();
            user.setUserName(username);
            user.setPassword(newPassword);
            user.setUserType(Integer.valueOf(userType));
            userService.registUser(user);
            map.put("code",0);
            map.put("msg","");
            map.put("data",0);
            map.put("count",0);
            return map;
        }catch (Exception e)
        {
            c_logger.error("registUser error",e);
            map.put("code",0);
            map.put("msg","");
            map.put("data",2);
            map.put("count",0);
            return map;
        }
    }
}
