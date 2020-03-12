package com.example.qzm.study.function.http.asynchttp1.config;

import com.example.qzm.study.function.http.asynchttp1.service.HttpReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Stone Mack on 2018/12/6.
 */
@Configuration
public class HttpReportConfig {
    /**
     * 初始化一个http上报服务
     */
    @Bean
    public HttpReportService httpReportService(){
        return HttpReportService.builder().build();
    }
}

