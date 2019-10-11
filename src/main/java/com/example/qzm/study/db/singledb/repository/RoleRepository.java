package com.example.qzm.study.db.singledb.repository;

import com.example.qzm.study.db.singledb.entity.Role;
import com.example.qzm.study.db.singledb.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @ClassName RoleRepository
 * @Description TODO
 * @Version 1.0
 **/
@Repository
public class RoleRepository {
    @Autowired
    private RoleMapper roleMapper;

    public void insert(Role role){
        roleMapper.insert(role);
    }

    public Role select(long roleId){
        return roleMapper.select(roleId);
    }

}
