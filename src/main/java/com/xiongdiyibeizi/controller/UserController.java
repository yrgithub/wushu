package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.service.api.UserService;
import com.xiongdiyibeizi.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger c_logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("changePassword")
    @ResponseBody
    public Object changePassword(@RequestParam("singleData")String singleData)
    {
        try{

            singleData = StringUtil.INST.decodeStrUrl(singleData);
            JSONObject decode = JSONObject.fromObject(singleData);
            int result = userService.changePassword(decode);
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","");
            map.put("count",1);
            map.put("data",result);
            return map;
        }catch (Exception e)
        {
            c_logger.error("changePassword error:",e);
            return null;
        }
    }

}
