package com.example.qzm.study.db.mysql.test;

import com.example.qzm.study.db.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName Test
 * @Description TODO
 * @Version 1.0
 **/
public class JavaSingleDataSourceTest {
    private static final Logger logger = LoggerFactory.getLogger(JavaSingleDataSourceTest.class);
    public static void main(String[] args) {
        DataSource singleDataSource = DataSourceUtil.singleDataSource();
        Connection connection = null;
        try {
            connection = singleDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select a.* from role a");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String gender = resultSet.getString(3);
                System.out.println("id:" + id + " username：" + name + " roleName：" + gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
