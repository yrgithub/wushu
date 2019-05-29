package com.xiongdiyibeizi.service.api;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.xiongdiyibeizi.bean.NewsWithBLOBs;
import com.xiongdiyibeizi.model.query.NewsQueryParam;

public interface NewsService {

	Map<String, Object> add(NewsWithBLOBs blobs);

	Map<String, Object> queryByPage(NewsQueryParam param) throws UnsupportedEncodingException;

	Map<String, Object> delete(String newsId);

	Map<String, Object> modify(NewsWithBLOBs blobs);

	Map<String, Object> queryById(String newsId) throws UnsupportedEncodingException;

}
