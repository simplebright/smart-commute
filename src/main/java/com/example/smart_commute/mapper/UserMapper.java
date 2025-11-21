package com.example.smart_commute.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.smart_commute.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);
    @Insert("INSERT INTO users(username,password,roles) VALUES (#{username},#{password},#{roles})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);
} 
