package com.xiongdiyibeizi.bean;

public class NewsWithBLOBs extends News {
    private byte[] content;

    private byte[] contentHtml;

    public byte[] getContent() {
        return content;
    }

    public NewsWithBLOBs(News news) {
		super();
		setContents(news.getContents());
		setNewsId(news.getNewsId());
		setNewsTitle(news.getNewsTitle());
		setShortContent(news.getShortContent());
		setShortTitle(news.getShortTitle());
		this.content = news.getContents().getBytes();
		this.contentHtml = news.getContentsHtml().getBytes();
	}

	public NewsWithBLOBs() {
		super();
	}

	public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(byte[] contentHtml) {
        this.contentHtml = contentHtml;
    }
}