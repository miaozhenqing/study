package com.example.qzm.study.db.mysql.single.service;

import com.example.qzm.study.db.mysql.single.entity.Role;
import com.example.qzm.study.db.mysql.single.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class RoleTestService {
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
            Role role = new Role(time, "李白" + String.valueOf(time).substring(10, 13));
//            repository.insert(role);
        }

    }

    public void select(long roleId) {
        Role role = repository.select(roleId);
        System.out.println(role.getUsername());
    }
}
