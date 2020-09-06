package com.itheima.mapper;

import com.itheima.domain.City;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //根据邮箱和密码登录
    User findByemailAndPwd(@Param("email")String email,@Param("password") String password) throws Exception;

    //查询所有
    List<User> findAll();

    //遍历所有城市
    List<City> findAllCity();

    //新增
    void add(User user);

    //根据id删除
    void deleteById(String id);

    //根据id查询
    User findById(String id);

    //修改
    void update(User user);
}
