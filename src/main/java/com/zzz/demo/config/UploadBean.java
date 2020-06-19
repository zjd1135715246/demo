package com.zzz.demo.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * <p>ClassName:     UploadBean
 * <p>Description:   TODO
 * <p>Author         maqp
 * <p>Version        V1.0
 * <p>Date           2017/1/22
 */
@Component
public class UploadBean {
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("10MB"));
        factory.setMaxRequestSize(DataSize.parse("10MB"));
        return factory.createMultipartConfig();
    }
}