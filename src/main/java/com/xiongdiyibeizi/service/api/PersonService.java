package com.xiongdiyibeizi.service.api;

import com.xiongdiyibeizi.bean.user.PersonInfo;

import java.util.List;
import java.util.Map;

public interface PersonService {

    /**
     * 获取公司信息
     * @param map
     * @return
     */
    List<PersonInfo> getBasicData(Map map);

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
    PersonInfo getDetailData(String strInfoId);

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
    int saveOrUpdate(String strInfoId, PersonInfo companyInfo);

}
