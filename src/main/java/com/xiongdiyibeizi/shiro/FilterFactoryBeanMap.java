package com.xiongdiyibeizi.shiro;

import com.xiongdiyibeizi.service.api.ShiroFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

public class FilterFactoryBeanMap{

    @Autowired
    private ShiroFilterService shiroFilterService;

    public  LinkedHashMap<String,String> getShiroFilterChainMap()
    {
        LinkedHashMap<String,String> filterMap = null;
        filterMap = shiroFilterService.getShiroFilter();
        if (filterMap.isEmpty()){
            filterMap = new LinkedHashMap<>();
        }
        return filterMap;
    }
}
