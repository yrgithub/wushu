package com.xiongdiyibeizi.service.api;

import com.xiongdiyibeizi.bean.user.CompanyPicture;

public interface CompanyPictureService {

    int insert2picture(CompanyPicture companyPicture);

    int update2picture(CompanyPicture companyPicture);

    CompanyPicture getPictureById(String strInfoId);

    int delPictureById(String strInfoId);
}
