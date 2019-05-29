package com.xiongdiyibeizi.model.query;

public class NewsQueryParam {

    private String newsTitle;
    
    private Integer startWith;
    
    private Integer pageSize;

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public Integer getStartWith() {
		return startWith;
	}

	public void setStartWith(Integer startWith) {
		this.startWith = startWith;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
