package com.xiongdiyibeizi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource(value = {"classpath:properties/server.properties"})
public class ServerPathConfig {

    @Value("${uploadPath}")
    private String uploadPath;
    @Value("${serverPath}")
    private String serverPath;
    @Value("${moduleName}")
    private String moduleName;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    @Override
    public String toString() {
        return "ServerPathConfig{" +
                "uploadPath='" + uploadPath + '\'' +
                ", serverPath='" + serverPath + '\'' +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
