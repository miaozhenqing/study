package com.example.qzm.study.db.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

/**
 * @ClassName InfluxTestClient
 * @Description TODO
 * @Version 1.0
 **/
public class InfluxTestClient {
    public static void main(String[] args) {
        //连接
        InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "manage");
        //选择数据库
        influxDB.setDatabase("study");
        //设置一条数据
        Point point = Point
                //表
                .measurement("role")
                //字段，值
                .addField("id", "10")
                .addField("username", "merry1")
                .build();
        influxDB.write(point);
    }
}
