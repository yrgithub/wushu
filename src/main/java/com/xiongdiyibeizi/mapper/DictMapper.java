package com.xiongdiyibeizi.mapper;

import com.xiongdiyibeizi.bean.user.Dict;

import java.util.List;

public interface DictMapper {

    /**
     * 根据字典类别获取字典信息
     * @param type
     * @return
     */
    List<Dict> getDictByType(String type);
}
