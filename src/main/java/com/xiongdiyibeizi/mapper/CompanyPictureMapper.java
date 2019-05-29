package com.xiongdiyibeizi.mapper;

import com.xiongdiyibeizi.bean.user.CompanyPicture;

public interface CompanyPictureMapper {

    int insert2picture(CompanyPicture companyPicture);

    int update2picture(CompanyPicture companyPicture);

    CompanyPicture getPictureById(String strInfoId);

    int delPictureById(String strInfoId);
}
