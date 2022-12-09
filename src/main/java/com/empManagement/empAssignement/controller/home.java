package com.empManagement.empAssignement.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/")
public class home {

    //    @GetMapping("/hello")
    @GetMapping(value = "/index", produces = MediaType.TEXT_HTML_VALUE)
    public String hello(){
        return "index";
    }

}
