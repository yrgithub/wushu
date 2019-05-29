package com.xiongdiyibeizi.mapper;

import com.xiongdiyibeizi.bean.user.PersonInfo;

import java.util.List;
import java.util.Map;

public interface PersonInfoMapper {

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
     * 根据公司主键获取公司的详细信息
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
     * 保存公司信息
     * @param companyInfo
     * @return
     */
    int insert2person(PersonInfo companyInfo);

    /**
     * 修改公司信息
     * @param companyInfo
     * @return
     */
    int update2person(PersonInfo companyInfo);
}