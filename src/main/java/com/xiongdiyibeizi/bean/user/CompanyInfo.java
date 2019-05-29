package com.xiongdiyibeizi.bean.user;

public class CompanyInfo {

    private String username;
    private String typeNameZh;

    private String companyInfoId;
    private String companyTel;
    private String companyAddress;
    private String companyArea;//公司所在区域的ID
    private String contentName;//公司所在区域的名字
    private String companyName;
    private String companyCorporate;
    private String companyPictureId;
    private String companyDescribe;

    private String companyPictureUrl;


    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyPictureUrl() {
        return companyPictureUrl;
    }

    public void setCompanyPictureUrl(String companyPictureUrl) {
        this.companyPictureUrl = companyPictureUrl;
    }

    public String getCompanyPictureId() {
        return companyPictureId;
    }

    public void setCompanyPictureId(String companyPictureId) {
        this.companyPictureId = companyPictureId;
    }

    public String getCompanyDescribe() {
        return companyDescribe;
    }

    public void setCompanyDescribe(String companyDescribe) {
        this.companyDescribe = companyDescribe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypeNameZh() {
        return typeNameZh;
    }

    public void setTypeNameZh(String typeNameZh) {
        this.typeNameZh = typeNameZh;
    }

    public String getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(String companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCorporate() {
        return companyCorporate;
    }

    public void setCompanyCorporate(String companyCorporate) {
        this.companyCorporate = companyCorporate;
    }
}
