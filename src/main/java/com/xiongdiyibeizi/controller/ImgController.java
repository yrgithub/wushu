package com.xiongdiyibeizi.controller;

import io.swagger.annotations.ApiParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mysql.jdbc.Blob;
import com.xiongdiyibeizi.bean.Img;
import com.xiongdiyibeizi.model.ResultBean;
import com.xiongdiyibeizi.model.query.ImgQueryParam;
import com.xiongdiyibeizi.service.api.ImgService;

@Controller
@RequestMapping("img")
public class ImgController {
	@Resource
	private ImgService imgService;
	
	//上传图片
	@ResponseBody
	@PostMapping("add")
	public ResultBean<Boolean> addImg(@ApiParam("要上传的图片") @RequestParam("imgFile")CommonsMultipartFile imgFile, @ApiParam("图片描述") @RequestParam("discrption") String discrption) throws IOException{
		ResultBean<Boolean> bean = new ResultBean<Boolean>();
		if(imgFile == null || imgFile.getSize() == 0){
			bean.setData(Boolean.FALSE);
			bean.setMessage("请选择要上传的图片");
			bean.setSuccess(Boolean.FALSE);
			return bean;
		}
		byte[] content = imgFile.getBytes();
		if(content == null || content.length == 0){
			bean.setData(Boolean.FALSE);
			bean.setMessage("请选择要上传的图片");
			bean.setSuccess(Boolean.FALSE);
			return bean;
		}
		String name = imgFile.getOriginalFilename();
		if(name == null || "".equals(name.trim())){
			bean.setData(Boolean.FALSE);
			bean.setMessage("文件格式不正确");
			bean.setSuccess(Boolean.FALSE);
			return bean;
		}
		String suffix = name.substring(name.lastIndexOf("."));
		if((!".jpg".equals(suffix))&& (!".png".equals(suffix))){
			bean.setData(Boolean.FALSE);
			bean.setMessage("只支持jpg和png格式的文件");
			bean.setSuccess(Boolean.FALSE);
			return bean;
		}
		Img img = new Img();
		img.setDiscribe(discrption);
		img.setImgContent(content);
		img.setName(name);
		Map<String, Object> map = imgService.add(img);
		bean.setData((Boolean)map.get("success"));
		bean.setMessage((String)map.get("message"));
		bean.setSuccess((Boolean)map.get("success"));
		return bean;
	}
	
	//修改图片信息
	@RequestMapping("update")
	@ResponseBody
	public ResultBean<Boolean> updateImage(@ApiParam("要上传的图片") @RequestParam("imgFile")CommonsMultipartFile imgFile, 
			@ApiParam("图片描述") @RequestParam("discrption") String discrption,
			@ApiParam("图片id") @RequestParam("imgId") String imgId){
		ResultBean<Boolean> bean = new ResultBean<Boolean>();
		if(imgId == null || "".equals(imgId.trim())){
			bean.setData(Boolean.FALSE);
			bean.setMessage("图片id不能为空");
			bean.setSuccess(Boolean.FALSE);
			return bean;
		}
		Img img = new Img();
		img.setImgId(imgId);
		if(imgFile != null && imgFile.getSize() != 0){
			byte[] content = imgFile.getBytes();
			if(content != null && content.length == 0){
				img.setImgContent(content);
				String name = imgFile.getOriginalFilename();
				if(name == null || "".equals(name.trim())){
					bean.setData(Boolean.FALSE);
					bean.setMessage("文件格式不正确");
					bean.setSuccess(Boolean.FALSE);
					return bean;
				}
				String suffix = name.substring(name.lastIndexOf("."));
				if((!".jpg".equals(suffix))&& (!".png".equals(suffix))){
					bean.setData(Boolean.FALSE);
					bean.setMessage("只支持jpg和png格式的文件");
					bean.setSuccess(Boolean.FALSE);
					return bean;
				}
				img.setName(name);
				img.setUploadTime(new Date());
			}
		}
		img.setDiscribe(discrption);
		Map<String, Object> map = imgService.update(img);
		bean.setData((Boolean)map.get("success"));
		bean.setMessage((String)map.get("message"));
		bean.setSuccess((Boolean)map.get("success"));
		return bean;
	}

	//删除图片
	@RequestMapping("delete")
	@ResponseBody
	public ResultBean<Boolean> deleteImage(@RequestParam("imgId") String imgId){
		ResultBean<Boolean> bean = new ResultBean<Boolean>();
		Map<String, Object> map = imgService.deleteImage(imgId);
		bean.setData((Boolean)map.get("success"));
		bean.setMessage((String)map.get("message"));
		bean.setSuccess((Boolean)map.get("success"));
		return bean;
	}
	
	//查询最新的五张图片
	@SuppressWarnings("unchecked")
	@RequestMapping("queryFive")
	@ResponseBody
	public ResultBean<List<Img>> queryFive(){
		ResultBean<List<Img>> bean = new ResultBean<List<Img>>();
		Map<String, Object> map = imgService.queryFive();
		bean.setData((List<Img>) map.get("data"));
		bean.setMessage((String)map.get("message"));
		bean.setSuccess((Boolean)map.get("success"));
		return bean;
	}
	
	//按条件查询
	@RequestMapping("query")
	@ResponseBody
	public ResultBean<List<Img>> query(@RequestBody ImgQueryParam param){
		ResultBean<List<Img>> bean = new ResultBean<List<Img>>();
		List<Img> imgs = imgService.query(param);
		bean.setData(imgs);
		bean.setMessage("查询成功");
		bean.setSuccess(Boolean.TRUE);
		return bean;
	}
	

	//下载单个图片
	@RequestMapping("queryContent")
	public void queryContent(@RequestParam("imgId") String imgId, HttpServletResponse response){
		Img img = imgService.queryById(imgId);
		//将中文的文件名编码后再放到http的响应头中去，编码之后浏览器收到后会自动解码
		String filename = null;
		try {
			filename = URLEncoder.encode(img.getName(),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		//设置参数，使得浏览器可以以下载的方式打开文件。
		response.setHeader("content-disposition", "attachement;filename="+filename);
 
		byte[] blob = img.getImgContent();
		//读进来后，再写到response.getOutputStream()去就可以了
		//相应的数据
		try {
			OutputStream out=response.getOutputStream();
			out.write(blob, 0, blob.length);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//下载单个图片
	@GetMapping("queryOne")
	@ResponseBody
	public ResultBean<Img> queryOne(@RequestParam("imgId") String imgId){
		ResultBean<Img> bean = new ResultBean<Img>();
		Img img = imgService.queryById(imgId);
		if(img != null){
			img.setImgContent(null);
		}
		bean.setMessage("查询成功");
		bean.setSuccess(true);
		bean.setData(img);
		return bean;
	}
}
