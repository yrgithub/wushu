package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.bean.user.CompanyInfo;
import com.xiongdiyibeizi.bean.user.CompanyPicture;
import com.xiongdiyibeizi.service.api.CompanyPictureService;
import com.xiongdiyibeizi.service.api.CompanyService;
import com.xiongdiyibeizi.service.api.UserService;
import com.xiongdiyibeizi.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("company")
public class CompanyController {

    private static final Logger c_logger = Logger.getLogger(CompanyController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyPictureService companyPictureService;

    /**
     *
     * @param companyName 公司名称
     * @param companyArea 所在区域代码,参照字典content_id
     * @param role
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("getBasicData")
    @ResponseBody
    public Object getBasicData(@RequestParam(value = "companyName",required = false)String companyName,
                               @RequestParam(value = "companyArea",required = false)String companyArea,
                               @RequestParam("role")String role,
                               @RequestParam("limit")int limit,
                               @RequestParam("page")int page)
    {
        try{

            companyName = StringUtil.INST.decodeStrUrl(companyName);
            companyArea = StringUtil.INST.decodeStrUrl(companyArea);
            Map map = new HashMap();
            map.put("companyName",companyName);
            map.put("companyArea",companyArea);
            map.put("role",role);
            map.put("start",(page-1)*limit);
            map.put("num",limit);

            List<CompanyInfo> companyInfoList = new ArrayList<>();
            companyInfoList = companyService.getBasicData(map);
            for (CompanyInfo companyInfo:companyInfoList){
                if(companyInfo.getCompanyPictureId() != null && companyInfo.getCompanyPictureId().length()>0) {
                    CompanyPicture companyPicture = companyPictureService.getPictureById(companyInfo.getCompanyPictureId());
                    companyInfo.setCompanyPictureUrl(companyPicture.getPicturePath());
                }
            }

            int count = companyService.getBasicDataCnt(map);
            map.clear();
            map.put("code",0);
            map.put("msg","");
            map.put("count",count);
            map.put("data",companyInfoList);

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
            CompanyInfo companyInfo = companyService.getDetailData(username);
            if (companyInfo.getCompanyPictureId() != null && companyInfo.getCompanyPictureId().length()>0) {
                CompanyPicture companyPicture = companyPictureService.getPictureById(companyInfo.getCompanyPictureId());
                companyInfo.setCompanyPictureUrl(companyPicture.getPicturePath());
            }
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","");
            map.put("count",1);
            map.put("data",companyInfo);
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
            int result = companyService.deleteById(strInfoId);
            int resultUser = userService.deleteByUsername(username);
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
            CompanyInfo companyInfo = null;
            if (singleData != null)
            {
                JSONObject object = JSONObject.fromObject(singleData);
                companyInfo =(CompanyInfo) JSONObject.toBean(object,CompanyInfo.class);
            }
            int result = companyService.saveOrUpdate(strInfoId,companyInfo);
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
