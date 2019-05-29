package com.xiongdiyibeizi.bean;

import java.util.Date;

public class News {
    private String newsId;

    private String newsTitle;

    private String shortTitle;

    private String shortContent;
    
    private String contents;
    
    private String contentsHtml;
    
    private Date updateTime;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContentsHtml() {
		return contentsHtml;
	}

	public void setContentsHtml(String contentsHtml) {
		this.contentsHtml = contentsHtml;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}