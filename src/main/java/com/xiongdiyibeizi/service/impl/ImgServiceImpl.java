package com.xiongdiyibeizi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiongdiyibeizi.bean.Img;
import com.xiongdiyibeizi.bean.ImgExample;
import com.xiongdiyibeizi.mapper.ImgMapper;
import com.xiongdiyibeizi.model.query.ImgQueryParam;
import com.xiongdiyibeizi.service.api.ImgService;

@Service
public class ImgServiceImpl implements ImgService{

	@Resource
	private ImgMapper imgMapper;
	
	@Override
	public Map<String, Object> add(Img img) {
		Map<String, Object> map = new HashMap<String, Object>();
		img.setImgId(UUID.randomUUID().toString());
		img.setUploadTime(new Date());
		int result = imgMapper.insertSelective(img);
		if(result > 0){
			map.put("success", Boolean.TRUE);
			map.put("message", "上传成功");
		}
		else{
			map.put("success", Boolean.FALSE);
			map.put("message", "上传失败");
		}
		return map;
	}

	@Override
	public Map<String, Object> update(Img img) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = imgMapper.updateByPrimaryKeySelective(img);
		if(result > 0){
			map.put("success", Boolean.TRUE);
			map.put("message", "修改成功");
		}
		else{
			map.put("success", Boolean.FALSE);
			map.put("message", "修改失败");
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteImage(String imgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = imgMapper.deleteByPrimaryKey(imgId);
		if(result > 0){
			map.put("success", Boolean.TRUE);
			map.put("message", "删除成功");
		}
		else{
			map.put("success", Boolean.FALSE);
			map.put("message", "该图片不存在");
		}
		return map;
	}

	@Override
	public Map<String, Object> queryFive() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Img> imgs = imgMapper.selectLatestFive();
		map.put("success", Boolean.TRUE);
		map.put("message", "查询成功");
		map.put("data", imgs);
		return map;
	}

	@Override
	public List<Img> query(ImgQueryParam param) {
		ImgExample example = setParam(param);
		return imgMapper.selectByExample(example);
	}

	private ImgExample setParam(ImgQueryParam param) {
		ImgExample example = new ImgExample();
		ImgExample.Criteria criteria = example.createCriteria();
		if(param.getDescribe() != null && (!"".equals(param.getDescribe()))){
			criteria.andDiscribeLike(param.getDescribe() + "%");
		}
		return example;
	}

	@Override
	public Img queryById(String imgId) {
		Img img = imgMapper.selectByPrimaryKey(imgId);
		return img;
	}

}
