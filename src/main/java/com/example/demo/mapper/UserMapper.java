package com.example.demo.mapper;

import com.example.demo.po.User;

import java.util.List;

/**
 * MyBatis中的mapper接口相当于以前的dao。但是区别在于，mapper仅仅是接口，我们不需要提供实现类
 */
public interface UserMapper {

    /**
     * MyBatis面向接口变成的两个一致:
     * 1、映射文件的nampespace要和mapper接口的全类型保持一致
     * 2、映射文件中的SQL语句的id要和mapper接口中的方法一致
     *
     * 表--实体类--mapper接口--映射文件
     */

    /**
     * 添加用户信息
     * 返回值是int表示查询结果的行数
     *
     * @return
     */
    int insertUser();

    /**
     * 更新用户
     */
    void updateUser();

    /**
     * 删除用户
     */
    void deleteUser();

    /**
     * 查询单个用户
     */
    User getUserById();

    /**
     * 查询所有
     */
    List<User> getAllUser();
}
