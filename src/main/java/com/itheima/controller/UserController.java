package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.City;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录操作
    @RequestMapping("/login")
    public String login(String email, String password) throws Exception {
        System.out.println(email + "," + password);
        //调用UserService中的查询方法
        User user = userService.findByemailAndPwd(email, password);
        //如果返回得user为空则跳回登录页面
        if (user == null) {
            return "login";
        } else {
            //如果返回的user不为空则说明匹配到了，跳到主页面
            return "main";
        }
    }

    //查询所有
    @RequestMapping("/list")
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       Model model) {
        //调用service层的查询所有方法
        List<User> list = userService.findAll(pageNum, pageSize);
        //分页处理把查询结果叫给pageInfo处理
        PageInfo<User> pageInfo = new PageInfo<>(list);
        //将pageInfo存入域对象中
        model.addAttribute(pageInfo);
        return "list";
    }

    //查询所有城市并将数据带回add.jsp
    @RequestMapping("/city")
    public void findAllCity(HttpServletResponse response) throws IOException {
        //调用service层的查询所有城市方法
        List<City> list = userService.findAllCity();
        //将查询结果转换成json数据
        String json = new ObjectMapper().writeValueAsString(list);
        response.getWriter().write(json);
    }

    //新增
    @RequestMapping("/add")
    public String Add(@RequestParam String name,
                      @RequestParam int age,
                      @RequestParam int gender,
                      @RequestParam String email,
                      @RequestParam String password,
                      @RequestParam String qq,
                      @RequestParam String address
    ) {
        //将前端数据封装进实体类
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setQq(qq);
        user.setAddress(address);
        //调用service层的add方法
        userService.add(user);
        return "main";
    }

    //根据id删除
    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam String id) {
        //调用service层的根据id删除
        userService.deleteById(id);
        return "main";
    }

    //根据id查询
    @RequestMapping("/findById")
    public String findById(@RequestParam String id, Model model) {
        User user = userService.findById(id);
        model.addAttribute(user);
        return "update";
    }

    //修改
    @RequestMapping("/update")
    public String update(@RequestParam String id,
                         @RequestParam String name,
                         @RequestParam int age,
                         @RequestParam int gender,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String qq,
                         @RequestParam String address
    ) {
        //将前端数据封装进实体类
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setQq(qq);
        user.setAddress(address);
        //调用service层的add方法
        userService.update(user);
        return "main";
    }
}
