package com.example.qzm.study.db.mysql.sharding.type_properties;

import com.example.qzm.study.db.mysql.single.entity.Role;
import com.example.qzm.study.db.mysql.single.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 根据properties配置文件分库分表
 **/
@Service
public class ShardingRoleService {
    @Autowired
    private RoleRepository repository;

    @PostConstruct
    public void init() {
        insert();
    }

    public void insert() {
        int num = 10;
        while (num-- > 0) {
            long time = System.currentTimeMillis();
            Role role = new Role(time, "李白" + String.valueOf(time).substring(8, 13));
            repository.insert(role);
        }
        selectAll();
    }

    public void selectAll() {
        List<Role> list = repository.selectAll();
        list.forEach(role -> System.out.println(role.getId() + "-" + role.getUsername() + "-" + role.getRoleName()));
    }
}
