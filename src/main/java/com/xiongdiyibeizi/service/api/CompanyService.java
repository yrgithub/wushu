package com.xiongdiyibeizi.service.api;

import com.xiongdiyibeizi.bean.user.CompanyInfo;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    /**
     * 获取公司信息
     * @param map
     * @return
     */
    List<CompanyInfo> getBasicData(Map map);

    /**
     * 获取公司信息的个数
     * @param map
     * @return
     */
    int getBasicDataCnt(Map map);

    /**
     * 根据主键获取数据
     * @param strInfoId
     * @return
     */
    CompanyInfo getDetailData(String strInfoId);

    /**
     * 根据主键删除
     * @param strInfoId
     * @return
     */
    int deleteById(String strInfoId);

    /**
     * 修改或者更新公司的信息
     * @param strInfoId
     * @param companyInfo
     * @return
     */
    int saveOrUpdate(String strInfoId,CompanyInfo companyInfo);
}
