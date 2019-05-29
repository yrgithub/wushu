package com.xiongdiyibeizi.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiongdiyibeizi.bean.News;
import com.xiongdiyibeizi.bean.NewsExample;
import com.xiongdiyibeizi.bean.NewsWithBLOBs;
import com.xiongdiyibeizi.mapper.NewsMapper;
import com.xiongdiyibeizi.model.query.NewsQueryParam;
import com.xiongdiyibeizi.service.api.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	@Resource 
	private NewsMapper newsMapper;
	
	@Override
	public Map<String, Object> add(NewsWithBLOBs blobs) {
		Map<String, Object> map = new HashMap<String, Object>();
		blobs.setNewsId(UUID.randomUUID().toString());
		if(blobs.getNewsTitle() == null || "".equals(blobs.getNewsTitle())){
			map.put("success", Boolean.FALSE);
			map.put("message", "新闻标题不能为空");
			return map;
		}
		if(blobs.getNewsTitle().length() > 10){
			blobs.setShortTitle(blobs.getNewsTitle().substring(0,10));
		}
		else{
			blobs.setShortTitle(blobs.getNewsTitle());
		}
		if(blobs.getContents() == null || "".equals(blobs.getContents())){
			map.put("success", Boolean.FALSE);
			map.put("message", "新闻内容不能为空");
			return map;
		}
		if(blobs.getContents().length() > 10){
			blobs.setShortContent(blobs.getContents().substring(0,10));
		}
		else{
			blobs.setShortContent(blobs.getContents());
		}
		int result = newsMapper.insertSelective(blobs);
		if(result == 1){
			map.put("success", Boolean.TRUE);
			map.put("message", "添加成功");
		}
		else{
			map.put("success", Boolean.FALSE);
			map.put("message", "添加失败");
		}
		return map;
	}

	@Override
	public Map<String, Object> queryByPage(NewsQueryParam param) throws UnsupportedEncodingException {
		List<NewsWithBLOBs> blobList = newsMapper.selectByExampleWithBLOBsByPage(setParam(param));
		List<News> newsList = new ArrayList<News>();
		for (NewsWithBLOBs newsWithBLOBs : blobList) {
			News news = newsWithBLOBs;
			news.setContents(new String(newsWithBLOBs.getContent(), "utf-8"));
			news.setContentsHtml(new String(newsWithBLOBs.getContentHtml(), "utf-8"));
			newsList.add(news);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", Boolean.TRUE);
		map.put("data", newsList);
		map.put("message", "查询成功");
		return map;
	}

	@Override
	public Map<String, Object> modify(NewsWithBLOBs blobs) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(blobs.getNewsId() == null || "".equals(blobs.getNewsId())){
			map.put("success", Boolean.FALSE);
			map.put("message", "新闻id不能为空");
			return map;
		}
		if(blobs.getNewsTitle() == null || "".equals(blobs.getNewsTitle())){
			map.put("success", Boolean.FALSE);
			map.put("message", "新闻标题不能为空");
			return map;
		}
		if(blobs.getNewsTitle().length() > 10){
			blobs.setShortTitle(blobs.getNewsTitle().substring(0,10));
		}
		else{
			blobs.setShortTitle(blobs.getNewsTitle());
		}
		if(blobs.getContents() == null || "".equals(blobs.getContents())){
			map.put("success", Boolean.FALSE);
			map.put("message", "新闻内容不能为空");
			return map;
		}
		if(blobs.getContents().length() > 10){
			blobs.setShortContent(blobs.getContents().substring(0,10));
		}
		else{
			blobs.setShortContent(blobs.getContents());
		}
		int result = newsMapper.updateByPrimaryKeyWithBLOBs(blobs);
		if(result == 1){
			map.put("success", Boolean.TRUE);
			map.put("message", "修改成功");
		}
		else{
			map.put("success", Boolean.FALSE);
			map.put("message", "修改失败");
		}
		return map;
	}

	private NewsExample setParam(NewsQueryParam param) {
		NewsExample example = new NewsExample();
		NewsExample.Criteria criteria = example.createCriteria();
		if(param.getStartWith() != null && param.getPageSize() != null){
			example.setStartWith(param.getStartWith());
			example.setPageSize(param.getPageSize());
		}
		if(param.getNewsTitle() != null && (!"".equals(param.getNewsTitle()))){
			criteria.andNewsTitleLike("%" + param.getNewsTitle() + "%");
		}
		return example;
	}

	@Override
	public Map<String, Object> delete(String newsId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = newsMapper.deleteByPrimaryKey(newsId);
		if(result > 0){
			map.put("success", Boolean.TRUE);
			map.put("message", "删除成功");
		}
		else{
			map.put("success", Boolean.FALSE);
			map.put("message", "该新闻不存在");
		}
		return map;
	}

	@Override
	public Map<String, Object> queryById(String newsId) throws UnsupportedEncodingException {
		NewsWithBLOBs blob = newsMapper.selectByPrimaryKey(newsId);
		blob.setContents(new String(blob.getContent(), "utf-8"));
		blob.setContentsHtml(new String(blob.getContentHtml(), "utf-8"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", Boolean.TRUE);
		map.put("data", blob);
		map.put("message", "查询成功");
		return map;
	}

}
