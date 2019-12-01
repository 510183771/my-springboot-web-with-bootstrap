package com.nick.springboot.controller;

import com.nick.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloWorld {

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @ResponseBody
    @RequestMapping(value = "/hello2")
    public String helloWorld2(@RequestParam("user") String user){
        if("aaa".equalsIgnoreCase(user)){
            throw new UserNotExistException();
        }

        return "Hello World";
    }

    @RequestMapping(value = "/testThymeleaf")
    public ModelAndView testThymeleaf(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("User","Nick");
        mv.setViewName("/test-thymeleaf.html");
        return mv;
    }

    @RequestMapping(value = "/")
    public String goToIndex() {
        return "login";
    }

    @RequestMapping(value = "/testThymeleaf2")
    public String testThymelea2(Map<String,Object> map){
        map.put("User","Nick");
        return "test-thymeleaf";
    }
}
