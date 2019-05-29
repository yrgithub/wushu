package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.bean.user.PersonInfo;
import com.xiongdiyibeizi.service.api.PersonService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("person")
public class PersonController {

    private static final Logger c_logger = Logger.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @RequestMapping("getBasicData")
    @ResponseBody
    public Object getBasicData(@RequestParam("firstValue")String firstValue,
                               @RequestParam("role")String role,
                               @RequestParam("limit")int limit,
                               @RequestParam("page")int page)
    {
        try{

            firstValue = StringUtil.INST.decodeStrUrl(firstValue);
            role = StringUtil.INST.decodeStrUrl(role);
            Map map = new HashMap();
            map.put("firstValue",firstValue);
            map.put("role",role);
            map.put("start",(page-1)*limit);
            map.put("num",limit);

            List<PersonInfo> personInfoList = personService.getBasicData(map);
            int count = personService.getBasicDataCnt(map);

            map.clear();
            map.put("code",0);
            map.put("msg","");
            map.put("count",count);
            map.put("data",personInfoList);

            return map;
        }catch (Exception e)
        {
            c_logger.error("getBasicData error:",e);
            return null;
        }
    }

    @RequestMapping("getDetailData")
    @ResponseBody
    public Object getBasicData(@RequestParam("username")String username)
    {
        try{

            username = StringUtil.INST.decodeStrUrl(username);
            PersonInfo personInfo = personService.getDetailData(username);
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","");
            map.put("count",1);
            map.put("data",personInfo);
            return map;
        }catch (Exception e)
        {
            c_logger.error("getDetailData error:",e);
            return null;
        }
    }

    @RequestMapping("deleteById")
    @ResponseBody
    public Object deleteById(@RequestParam("strInfoId")String strInfoId,
                             @RequestParam("username")String username)
    {
        try{

            strInfoId = StringUtil.INST.decodeStrUrl(strInfoId);
            int result = personService.deleteById(strInfoId);
            int resultId = userService.deleteByUsername(username);
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","");
            map.put("count",0);
            map.put("data",result);
            return map;
        }catch (Exception e)
        {
            c_logger.error("deleteById error:",e);
            return null;
        }
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(@RequestParam("strInfoId")String strInfoId,
                               @RequestParam("singleData")String singleData)
    {
        try{
            singleData = StringUtil.INST.decodeStrUrl(singleData);
            strInfoId = StringUtil.INST.decodeStrUrl(strInfoId);
            PersonInfo personInfo = null;
            if (singleData != null)
            {
                JSONObject object = JSONObject.fromObject(singleData);
                personInfo =(PersonInfo) JSONObject.toBean(object,PersonInfo.class);
            }
            int result = personService.saveOrUpdate(strInfoId,personInfo);
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","");
            map.put("count",0);
            map.put("data",result);
            return map;
        }catch (Exception e)
        {
            c_logger.error("saveOrUpdate error:",e);
            return null;
        }
    }
}
