package com.xiongdiyibeizi.controller;

import com.xiongdiyibeizi.bean.user.CompanyPicture;
import com.xiongdiyibeizi.common.Contains;
import com.xiongdiyibeizi.config.ServerPathConfig;
import com.xiongdiyibeizi.service.api.CompanyPictureService;
import com.xiongdiyibeizi.service.api.CompanyService;
import com.xiongdiyibeizi.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("companyPicture")
@Controller
public class CompanyPictureController {

    private static final Logger c_logger = Logger.getLogger(CompanyPictureController.class);

    @Autowired
    private CompanyPictureService companyPictureService;

    @Resource
    private ServerPathConfig serverPathConfig;

    @RequestMapping(value = "uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public Object uploadPicture(MultipartFile file,
                                HttpServletRequest request)
    {
        Map map = new HashMap();
        try{
            //String path = request.getSession().getServletContext().getRealPath("\\upload\\");
            String realName = file.getOriginalFilename();//上传文件的真实名称
            String suffixName = realName.substring(realName.lastIndexOf("."));//文件后缀名
            String hash = StringUtil.INST.getGeneralKey().replaceAll("-","");//自定义随机数作为文件名
            String fileName = hash + suffixName;
            String relativePath = serverPathConfig.getModuleName() +fileName;
            String filePath = serverPathConfig.getUploadPath()+relativePath;
            String ServerPath = serverPathConfig.getServerPath()+relativePath;

            System.out.println("filePath: "+filePath);
            System.out.println("ServerPath: "+ServerPath);
            File tempFile = new File(filePath);
            if (!tempFile.getParentFile().exists()){
                tempFile.getParentFile().mkdirs();
            }
            file.transferTo(tempFile);
            CompanyPicture companyPicture = new CompanyPicture();
            String pictureId = StringUtil.INST.getGeneralKey();
            companyPicture.setPictureId(pictureId);
            companyPicture.setOriginalName(realName);
            companyPicture.setPictureName(fileName);
            companyPicture.setPicturePath(relativePath);
            companyPicture.setSuffixName(suffixName);
            companyPictureService.insert2picture(companyPicture);

            map.put("code",0);
            map.put("msg","");
            map.put("count",0);
            map.put("data",companyPicture);
            return map;
        }catch (Exception e)
        {
            c_logger.error("uploadPicture error:",e);
            map.put("code",0);
            map.put("msg","");
            map.put("count",0);
            map.put("data",1);
            return map;
        }
    }
}
