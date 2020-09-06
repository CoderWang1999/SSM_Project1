package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/path")
public class PathController {
    @RequestMapping("/{pathName}")
    public String gotoJsp(@PathVariable("pathName") String path){
        return path;
    }
}
