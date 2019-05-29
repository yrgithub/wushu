package com.xiongdiyibeizi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiongdiyibeizi.bean.News;
import com.xiongdiyibeizi.bean.NewsWithBLOBs;
import com.xiongdiyibeizi.model.ResultBean;
import com.xiongdiyibeizi.model.query.NewsQueryParam;
import com.xiongdiyibeizi.service.api.NewsService;

@Controller
@RequestMapping("news")
@Api(description="新闻接口", value = "NewsController")
public class NewsController {
	
	@Resource
	private NewsService newsService;
	
	@PostMapping("add")
	@ResponseBody
	@ApiOperation(value = "添加新闻", notes = "添加新闻")
	public ResultBean<Boolean> add(@ApiParam("新闻实体") @RequestBody News news){
		ResultBean<Boolean> result = new ResultBean<>();
		if(news.getNewsTitle() == null || "".equals(news.getNewsTitle())){
			result.setSuccess(Boolean.FALSE);
			result.setMessage("新闻标题不能为空");
			result.setData(Boolean.FALSE);
			return result;
		}
		if(news.getContents() == null || "".equals(news.getContents())){
			result.setSuccess(Boolean.FALSE);
			result.setMessage("新闻内容不能为空");
			result.setData(Boolean.FALSE);
			return result;
		}
		NewsWithBLOBs blobs = new NewsWithBLOBs(news);
		
		Map<String, Object> map = newsService.add(blobs);
		result.setSuccess((Boolean) map.get("success"));
		result.setMessage((String) map.get("message"));
		result.setData((Boolean) map.get("success"));
		return result;
	}	
	
	@PostMapping("modify")
	@ResponseBody
	@ApiOperation(value = "修改新闻", notes = "修改新闻")
	public ResultBean<Boolean> modify(@ApiParam("新闻实体") @RequestBody News news){
		ResultBean<Boolean> result = new ResultBean<>();
		if(news.getNewsId() == null || "".equals(news.getNewsId())){
			result.setSuccess(Boolean.FALSE);
			result.setMessage("新闻id不能为空");
			result.setData(Boolean.FALSE);
			return result;
		}
		if(news.getNewsTitle() == null || "".equals(news.getNewsTitle())){
			result.setSuccess(Boolean.FALSE);
			result.setMessage("新闻标题不能为空");
			result.setData(Boolean.FALSE);
			return result;
		}
		if(news.getContents() == null || "".equals(news.getContents())){
			result.setSuccess(Boolean.FALSE);
			result.setMessage("新闻内容不能为空");
			result.setData(Boolean.FALSE);
			return result;
		}
		NewsWithBLOBs blobs = new NewsWithBLOBs(news);
		
		Map<String, Object> map = newsService.modify(blobs);
		result.setSuccess((Boolean) map.get("success"));
		result.setMessage((String) map.get("message"));
		result.setData((Boolean) map.get("success"));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("queryByPage")
	@ResponseBody
	@ApiOperation(value = "分页查询新闻", notes = "分页查询新闻")
	public ResultBean<List<News>> queryByPage(@ApiParam("新闻查询参数实体") @RequestBody NewsQueryParam param) throws UnsupportedEncodingException{
		ResultBean<List<News>> result = new ResultBean<List<News>>();
		Map<String, Object> map = newsService.queryByPage(param);
		result.setSuccess((Boolean) map.get("success"));
		result.setMessage((String) map.get("message"));
		result.setData((List<News>) map.get("data"));
		return result;
	}
	
	@GetMapping("queryById")
	@ResponseBody
	@ApiOperation(value = "分页查询新闻", notes = "分页查询新闻")
	public ResultBean<News> queryById(@ApiParam("新闻查询参数实体") @RequestParam("newsId") String newsId) throws UnsupportedEncodingException{
		ResultBean<News> result = new ResultBean<News>();
		Map<String, Object> map = newsService.queryById(newsId);
		result.setSuccess((Boolean) map.get("success"));
		result.setMessage((String) map.get("message"));
		result.setData((News) map.get("data"));
		return result;
	}
	
	@PostMapping("delete")
	@ResponseBody
	@ApiOperation(value = "删除新闻", notes = "删除新闻")
	public ResultBean<Boolean> delete(@ApiParam("新闻id") @RequestParam("newsId") String newsId){
		ResultBean<Boolean> result = new ResultBean<Boolean>();
		Map<String, Object> map = newsService.delete(newsId);
		result.setSuccess((Boolean) map.get("success"));
		result.setMessage((String) map.get("message"));
		result.setData((Boolean) map.get("success"));
		return result;
	}
}
