package com.xiongdiyibeizi.bean;

import java.util.Date;

public class Img {
    private String imgId;

    private Integer type;

    private String discribe;

    private String name;

    private Date uploadTime;

    private byte[] imgContent;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte[] getImgContent() {
        return imgContent;
    }

    public void setImgContent(byte[] imgContent) {
        this.imgContent = imgContent;
    }
}