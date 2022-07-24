package com.example.demo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {
    /**
     * 2.获取SqlSession,
     * openSession默认值为false；需要手动提交事务
     * openSession默认值为true时自动提交事务
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        try {
            //读取核心配置文件作为输入流
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //获取sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e){
            e.printStackTrace();
        }
        return sqlSession;
    }
}
