package com.jason.mapper;

import com.jason.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {

    @Insert("insert into role(id,name,title,world,img) values(next value for MYCATSEQ_GLOBAL,#{name},#{title},#{world},#{img})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int insertRoleUseId(Role role);
}
