package com.xiongdiyibeizi.service.api;

import com.xiongdiyibeizi.bean.user.Dict;

import java.util.List;

public interface DictService {

    /**
     * 根据字典类别获取字典信息
     * @param type
     * @return
     */
    List<Dict> getDictByType(String type);
}
