package com.xiongdiyibeizi.service.impl;

import com.xiongdiyibeizi.bean.user.CompanyPicture;
import com.xiongdiyibeizi.mapper.CompanyPictureMapper;
import com.xiongdiyibeizi.service.api.CompanyPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyPictureServiceImpl implements CompanyPictureService {

    @Autowired
    private CompanyPictureMapper companyPictureMapper;
    @Override
    public int insert2picture(CompanyPicture companyPicture) {
        return companyPictureMapper.insert2picture(companyPicture);
    }

    @Override
    public int update2picture(CompanyPicture companyPicture) {
        return companyPictureMapper.update2picture(companyPicture);
    }

    @Override
    public CompanyPicture getPictureById(String strInfoId) {
        return companyPictureMapper.getPictureById(strInfoId);
    }

    @Override
    public int delPictureById(String strInfoId) {
        return companyPictureMapper.delPictureById(strInfoId);
    }
}
