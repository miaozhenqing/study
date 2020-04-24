package com.example.qzm.study.db.mysql.single.repository;

import com.example.qzm.study.db.mysql.single.entity.Role;
import com.example.qzm.study.db.mysql.single.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    public List<Role> selectAll(){
        return roleMapper.selectAll();
    }
}
