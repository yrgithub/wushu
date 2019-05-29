package com.xiongdiyibeizi.service.impl;

import com.xiongdiyibeizi.bean.user.Dict;
import com.xiongdiyibeizi.mapper.DictMapper;
import com.xiongdiyibeizi.service.api.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictMapper;
    @Override
    public List<Dict> getDictByType(String type) {
        return dictMapper.getDictByType(type);
    }
}
