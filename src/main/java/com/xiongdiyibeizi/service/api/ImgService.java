package com.xiongdiyibeizi.service.api;

import java.util.List;
import java.util.Map;

import com.xiongdiyibeizi.bean.Img;
import com.xiongdiyibeizi.model.query.ImgQueryParam;

public interface ImgService {

	Map<String, Object> add(Img img);

	Map<String, Object> update(Img img);

	Map<String, Object> deleteImage(String imgId);

	Map<String, Object> queryFive();

	List<Img> query(ImgQueryParam param);

	Img queryById(String imgId);

}
