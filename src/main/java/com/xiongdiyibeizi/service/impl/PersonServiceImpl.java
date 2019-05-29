package com.xiongdiyibeizi.service.impl;

import com.xiongdiyibeizi.bean.user.PersonInfo;
import com.xiongdiyibeizi.mapper.PersonInfoMapper;
import com.xiongdiyibeizi.mapper.UserMapper;
import com.xiongdiyibeizi.service.api.PersonService;
import com.xiongdiyibeizi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<PersonInfo> getBasicData(Map map) {
        return personInfoMapper.getBasicData(map);
    }

    @Override
    public int getBasicDataCnt(Map map) {
        return personInfoMapper.getBasicDataCnt(map);
    }

    @Override
    public PersonInfo getDetailData(String strInfoId) {
        return personInfoMapper.getDetailData(strInfoId);
    }

    @Override
    public int deleteById(String strInfoId) {
        return personInfoMapper.deleteById(strInfoId);
    }

    @Override
    public int saveOrUpdate(String strInfoId,PersonInfo personInfo) {
        if (strInfoId == null || strInfoId.length()<=0)
        {
            String uuid = StringUtil.INST.getGeneralKey();
            personInfo.setPersonInfoId(uuid);
            personInfoMapper.insert2person(personInfo);

            Map map = new HashMap();
            map.put("createBy",uuid);
            map.put("username",personInfo.getUsername());
            userMapper.updateCreateBy(map);
        }
        else {
            personInfo.setPersonInfoId(strInfoId);
            personInfoMapper.update2person(personInfo);
        }
        return 1;
    }
}
