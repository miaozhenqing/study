//package com.example.qzm.study.db.mysql.test;
//
//import com.example.qzm.study.db.DataSourceUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName Test
// * @Description TODO
// * @Version 1.0
// **/
//public class JavaShardingDataSourceTest {
//    private static final Logger logger = LoggerFactory.getLogger(JavaShardingDataSourceTest.class);
//
//    public static void main(String[] args) {
//        DataSource singleDataSource = DataSourceUtil.singleDataSource();
//        Connection connection = null;
//
//    }
//
//    /**
//     * 数据源
//     */
//    private Map<String, DataSource> dataSourceMap() {
//        Map<String, DataSource> map = new HashMap<>();
//        map.put("ds0", DataSourceUtil.shardingDataSource(0));
//        map.put("ds1", DataSourceUtil.shardingDataSource(1));
//        return map;
//    }
//    /**
//     * 配置表规则
//     */
////    private TableRuleConfiguration roleConfiguration(){
////        TableRuleConfiguration tableRule=new TableRuleConfiguration();
////        return tableRule;
////    }
//}
