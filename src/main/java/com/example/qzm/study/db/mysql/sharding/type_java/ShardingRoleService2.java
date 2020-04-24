package com.example.qzm.study.db.mysql.sharding.type_java;

import com.example.qzm.study.db.DataSourceUtil;
import com.example.qzm.study.db.mysql.single.entity.Role;
import com.example.qzm.study.db.mysql.single.repository.RoleRepository;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 根据java编码方式分库分表
 **/
@Service
public class ShardingRoleService2 {
    @Autowired
    private RoleRepository repository;

    private void sss() throws Exception{
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        Map<String, Object> configMap = new HashMap<>();
        Properties props = new Properties();
        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap(), shardingRuleConfig, configMap, props);
    }

    /**
     * 数据源
     */
    private Map<String, DataSource> dataSourceMap() {
        Map<String, DataSource> map = new HashMap<>();
        map.put("ds0", DataSourceUtil.shardingDataSource(0));
        map.put("ds1", DataSourceUtil.shardingDataSource(1));
        return map;
    }

    /**
     * 配置表规则
     */
    private TableRuleConfiguration ruleConfiguration() {
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        tableRule.setActualDataNodes("ds$->{0..1}.role_$->{0..1}");
        tableRule.setLogicTable("role");
        tableRule.setDatabaseShardingStrategyConfig(databaseShardingStrategyConfig());
        tableRule.setTableShardingStrategyConfig(tableShardingStrategyConfig());
        tableRule.setKeyGeneratorColumnName("id");
        tableRule.setKeyGenerator(new DefaultKeyGenerator());
        return tableRule;
    }

    private ShardingStrategyConfiguration databaseShardingStrategyConfig() {
        ShardingStrategyConfiguration database = new InlineShardingStrategyConfiguration("username", "ds$->{id % 2}");
        return database;
    }

    private ShardingStrategyConfiguration tableShardingStrategyConfig() {
        ShardingStrategyConfiguration table = new InlineShardingStrategyConfiguration("username", "role_$->{username%2}");
        return table;
    }
}
