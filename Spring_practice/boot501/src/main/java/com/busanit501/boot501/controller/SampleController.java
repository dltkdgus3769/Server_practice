package com.busanit501.boot501.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    //레거시에서 앞단 jsp 사용.
    //부트에서 앞단 타임리프 사용. 확장자 .html 동일.
    public void hello(Model model) {
        model.addAttribute("msg", "Hello World");
    }
    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }

}
