package com.busanit501.spring_prac.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public void hello() {

        log.info("hello");
    }

    @GetMapping("/hello2")
    public String hello2() {

        log.info("hello2");
        return "helloTest";
    }

    @GetMapping("/foodList")
    public String foodList() {

        log.info("foodList");
        return "/food/list";
    }

    
}
