package com.xmxisme.uaa.mapper;


import com.xmxisme.uaa.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 */
@Mapper
public interface  UsersMapper {

    @Select("SELECT u.username, u.password, r.role_name\n" +
            "FROM users u\n" +
            "JOIN user_roles ur ON u.id = ur.user_id\n" +
            "JOIN roles r ON ur.role_id = r.id\n" +
            "WHERE u.username = #{username}\n")
    Users getUsersByUsername(String username);
}
