package com.xiongdiyibeizi.service.impl;

import com.xiongdiyibeizi.bean.user.CompanyInfo;
import com.xiongdiyibeizi.mapper.CompanyInfoMapper;
import com.xiongdiyibeizi.mapper.UserMapper;
import com.xiongdiyibeizi.service.api.CompanyService;
import com.xiongdiyibeizi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<CompanyInfo> getBasicData(Map map) {
        return companyInfoMapper.getBasicData(map);
    }

    @Override
    public int getBasicDataCnt(Map map) {
        return companyInfoMapper.getBasicDataCnt(map);
    }

    @Override
    public CompanyInfo getDetailData(String strInfoId) {
        return companyInfoMapper.getDetailData(strInfoId);
    }

    @Override
    public int deleteById(String strInfoId) {
        return companyInfoMapper.deleteById(strInfoId);
    }

    @Override
    public int saveOrUpdate(String strInfoId,CompanyInfo companyInfo) {
        if (strInfoId == null || strInfoId.length()<=0)
        {
            String uuid = StringUtil.INST.getGeneralKey();
            companyInfo.setCompanyInfoId(uuid);
            companyInfoMapper.insert2company(companyInfo);

            Map map = new HashMap();
            map.put("createBy",uuid);
            map.put("username",companyInfo.getUsername());
            userMapper.updateCreateBy(map);
        }
        else {
            companyInfo.setCompanyInfoId(strInfoId);
            companyInfoMapper.update2company(companyInfo);
        }
        return 1;
    }
}
