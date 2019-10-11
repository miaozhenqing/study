package com.example.qzm.study.db.singledb.mapper;

import com.example.qzm.study.db.singledb.entity.Role;
import org.apache.ibatis.annotations.*;


import java.util.Map;

/**
 * @ClassName RoleMapper
 * @Description TODO
 * @Version 1.0
 **/
@Mapper
public interface RoleMapper {
    @Insert("INSERT INTO `role`(`username`, `roleName`, `gender`, `level`, `exp`, `gold`, `gp`, `modifyTime`, `createTime`)  " +
            "VALUES (#{username}, #{roleName}, #{gender}, #{level}, #{exp}, #{gold}, #{gp}, #{modifyTime}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Role role);

    @Select("SELECT * from role where id=#{id}")
    Role select(final @Param("id") long roleId);

    @Delete("delete a from role a where a.id=#{id}")
    void delete(final @Param("id") long roleId);

    @Delete("update role a set (#{updateData}) where a.id=#{id}")
    void update(@Param("updateData") Map<String, Object> updateMap, @Param("id") long id);
}
