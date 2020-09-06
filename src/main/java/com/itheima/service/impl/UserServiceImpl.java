package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.City;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    //根据邮箱和密码登录
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByemailAndPwd(String email, String password) throws Exception{
        return userMapper.findByemailAndPwd(email,password);
    }

    //查询所有
    @Override
    public List<User> findAll(Integer pageNum, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.findAll();
    }

    @Override
    public List<City> findAllCity() {
        return userMapper.findAllCity();
    }

    //新增
    @Override
    public void add(User user) {
        String uid= UUID.randomUUID().toString();
        String id = uid.substring(1, 6);
        user.setId(id);
        userMapper.add(user);
    }

    //根据id删除
    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    //根据id查询
    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }

    //修改
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

}
