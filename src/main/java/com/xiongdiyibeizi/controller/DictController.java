package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.bean.user.Dict;
import com.xiongdiyibeizi.service.api.DictService;
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
@RequestMapping("dict")
public class DictController {

    private static final Logger c_logger = Logger.getLogger(DictController.class);

    @Autowired
    private DictService dictService;
    @RequestMapping("getDictByType")
    @ResponseBody
    public Object getDictByType(@RequestParam(value = "dictType") String dictType)
    {
        try{
            List<Dict> dictList = dictService.getDictByType(dictType);
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","");
            map.put("count",0);
            map.put("data",dictList);
            return map;
        }catch (Exception e){
            c_logger.error("getDictByType error:",e);
            return null;
        }
    }
}
