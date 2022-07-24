package com.example.demo.dao;

import com.example.demo.mapper.ParamMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import org.apache.ibatis.session.SqlSession;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static com.example.demo.utils.SqlSessionUtils.getSqlSession;

public class TestSqlSession {

    @Test
    public void testCRUD() {
        //1.获取SqlSession
        SqlSession sqlSession = getSqlSession();

        //3.获取mapper接口对象,底层会自动实现接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4.测试功能CRUD
        // 新增
        //int res = mapper.insertUser();
        //JDBC连接方式,需要手动提交事务
        //sqlSession.commit();
        //System.out.println("res:" + res);

        // 修改
        //mapper.updateUser();

        //删除
        //mapper.deleteUser();

        //查询单个
        //User userById = mapper.getUserById();
        //System.out.println(userById);
        //User(id=1, username=张三, password=123, age=23, sex=男, email=1234@q.com)

        //查询所有
        List<User> user_list = mapper.getAllUser();
        user_list.forEach(user -> System.out.println(user));
        /**
         * User(id=1, username=张三, password=123, age=23, sex=男, email=1234@q.com)
         * User(id=2, username=admin, password=123, age=23, sex=男, email=1234@q.com)
         */
    }

    /**
     * ${}本质上是字符拼接
     * #{}本质上是占位符赋值
     * MyBatis获取参数值的各种情况:
     * 1、mapper接口方法的参数为单个的字面变量类型
     * 可以通过${}或者#{}以任意的名称获取参数值,单需要注意${}的单引号问题
     * 2、mapper接口方法的参数为多个时
     * 此时MyBatis会自动将这些参数放在一个map集合中，以两种方式进行存储
     * a>以arg0,arg1…为键，以参数为值；
     * b>以param1,param2…为键，以参数为值；
     * 3、mapper接口方法的参数为多个时，可以手动将这些参数放在一个map中存储
     * 4、mapper接口的参数是实体类型的参数
     * 只要通过#{}或${}以属性的方式访问属性值即可
     * 5、使用@Param注解命名参数
     * 此时MyBatis会将这些参数放在map集合中，以两种方式进行存储
     * a>以@Param注解的value属性值为键，以参数为值；
     * b>以param1,param2…为键，以参数为值；
     * 只需要通过${}和#{}访问map集合的键就可以获取相对应的值
     */

    private String username = "admin";
    private String password = "123";

    // 情况1:mapper接口方法的参数为单个的字面变量类型
    @Test
    public void testParam() {
        //1.获取SqlSession
        SqlSession sqlSession = getSqlSession();

        //3.获取mapper接口对象,底层会自动实现接口
        ParamMapper mapper = sqlSession.getMapper(ParamMapper.class);

        List<User> user_list = mapper.getUserByName(username);
        user_list.forEach(user -> System.out.println(user));
    }

    // 2、mapper接口方法的参数为多个时
    @Test
    public void checkLogin() {
        //1.获取SqlSession
        SqlSession sqlSession = getSqlSession();

        //3.获取mapper接口对象,底层会自动实现接口
        ParamMapper mapper = sqlSession.getMapper(ParamMapper.class);

        User user = mapper.checkLogin(username, password);
        System.out.println(user);
    }

    //3、mapper接口方法的参数为多个时，可以手动将这些参数放在一个map中存储
    @Test
    public void checkLoginByMap() {
        //1.获取SqlSession
        SqlSession sqlSession = getSqlSession();

        //3.获取mapper接口对象,底层会自动实现接口
        ParamMapper mapper = sqlSession.getMapper(ParamMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    //4、mapper接口的参数是实体类型的参数
    @Test
    public void createUser() {
        //1.获取SqlSession
        SqlSession sqlSession = getSqlSession();

        //3.获取mapper接口对象,底层会自动实现接口
        ParamMapper mapper = sqlSession.getMapper(ParamMapper.class);

        mapper.createUser(new User(null,"大先生","111",18,"男","hacker@evil.com"));
    }

    //5、使用@Param注解命名参数(类似第三种情况)
    @Test
    public void checkLoginByParam() {
        //1.获取SqlSession
        SqlSession sqlSession = getSqlSession();

        //3.获取mapper接口对象,底层会自动实现接口
        ParamMapper mapper = sqlSession.getMapper(ParamMapper.class);

        User user = mapper.checkLoginByParam(username, password);
        System.out.println(user);
    }
}
