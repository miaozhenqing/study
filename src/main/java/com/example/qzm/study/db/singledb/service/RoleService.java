package com.example.qzm.study.db.singledb.service;

import com.example.qzm.study.db.singledb.entity.Role;
import com.example.qzm.study.db.singledb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    @PostConstruct
    public void init(){
        insert();
    }

    public void insert() {
        Role role = new Role("李白2", "李白");
        repository.insert(role);
    }

    public void select(long roleId) {
        Role role = repository.select(roleId);
        System.out.println(role.getUserName());
    }
}
