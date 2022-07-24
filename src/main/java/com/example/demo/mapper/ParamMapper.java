package com.example.demo.mapper;

import com.example.demo.po.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ParamMapper {
    List<User> getUserByName(String username);

    User checkLogin(String username, String password);

    User checkLoginByMap(Map<String, Object> map);

    int createUser(User user);

    User checkLoginByParam(@Param("username") String username,@Param("password") String password);
}
