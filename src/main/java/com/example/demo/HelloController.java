package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello()
    {
        return "hello world";
    }
    @GetMapping("/")
    public String hello_html(ModelMap map)
    {
        map.addAttribute("host", "http://114.51.4.0");
        return "index";
    }
}
