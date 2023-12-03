package com.example.demo.controller;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello()
    {
        return "hello world";
    }
    @ResponseBody
    @GetMapping("/hello/{id}")
    public String helloId(@PathVariable("id") String id)
    {
        return "hello, "+id;
    }
    @GetMapping("/")
    public String hello_html(ModelMap map)
    {
        map.addAttribute("host", "http://127.0.0.1");
        return "index";
    }
    @ResponseBody
    @RequestMapping("/echo/**")
    public String echoPath(HttpServletRequest request){
        return request.getServletPath();
    }

    @Autowired
    DataSource datasrc;

    @ResponseBody
    @RequestMapping("/property")
    public Object req_property(){
        return datasrc;
    }
}
