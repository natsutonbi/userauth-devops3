package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello()
    {
        return "hello world";
    }
    @GetMapping("/")
    public String hello_html(ModelMap map)
    {
        map.addAttribute("host", "http://127.0.0.1");
        return "index";
    }
}
