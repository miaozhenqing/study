package com.example.qzm.study.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

/**
 * @ClassName DataSourceUtil
 * @Description TODO
 * @Version 1.0
 **/
public class DataSourceUtil {
    public static DataSource singleDataSource(){
        String driver="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/study?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String user="root";
        String passward="manage";
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder
                .create()
                .url(url)
                .username(user)
                .password(passward)
                .type(DruidDataSource.class)
                .driverClassName(driver);
        return dataSourceBuilder.build();
    }
    public static DataSource shardingDataSource(int ds){
        String driver="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/study_%s?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String user="root";
        String passward="manage";
        String type="org.apache.commons.dbcp.BasicDataSource";
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder
                .create()
                .url(String.format(url, ds))
                .username(user)
                .password(passward)
                .type(DruidDataSource.class)
                .driverClassName(driver);
        return dataSourceBuilder.build();
    }
}
