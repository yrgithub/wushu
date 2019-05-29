package com.xiongdiyibeizi.service.impl;

import com.xiongdiyibeizi.bean.user.ShiroFilterDefinition;
import com.xiongdiyibeizi.mapper.ShiroFilterMapper;
import com.xiongdiyibeizi.service.api.ShiroFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroFilterServiceImpl implements ShiroFilterService {

    @Autowired
    private ShiroFilterMapper shiroFilterMapper;

    @Override
    public LinkedHashMap<String, String> getShiroFilter() {

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        List<ShiroFilterDefinition> definitionList = shiroFilterMapper.getShiroFilter();
        if (definitionList == null || definitionList.isEmpty()){
            return null;
        }
        for (ShiroFilterDefinition definition:definitionList){
            filterMap.put(definition.getFilterUrl(),definition.getFilterName());
        }
        return filterMap;
    }
}
