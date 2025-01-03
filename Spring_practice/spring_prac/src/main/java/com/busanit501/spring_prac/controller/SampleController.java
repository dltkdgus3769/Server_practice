package com.busanit501.spring_prac.controller;

import com.busanit501.spring_prac.dto.TodoDTO;
import jdk.vm.ci.meta.Local;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


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

    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1:"+name);
        log.info("ex1:"+age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name="name", defaultValue = "LSH") String name,
                    @RequestParam(name="age", defaultValue = "30") int age){
        log.info("ex2:"+name);
        log.info("ex2:"+age);
    }


    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3:"+dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("ex4:");
        model.addAttribute("msg"," <script>\n" +
                "                    alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" +
                "                </script>");
    }

    @GetMapping("/ex5")
    //localhost:8080/ex5?title=lsh&writer=이상현
    public void ex5(TodoDTO todoDTO, Model model){
        log.info("ex5:"+todoDTO);

    }

    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes){
        log.info("ex6:");
        redirectAttributes.addAttribute("msg","test data");
        redirectAttributes.addFlashAttribute("msg2","일회용 데이터");

        return "redirect:/ex7";

    }
    @GetMapping("/ex7")
    public void ex7(String msg, Model model){
        log.info("ex7:");
        model.addAttribute("msg",msg);
    }

    @GetMapping("/ex8")
    public void ex8(String name, int age){
        log.info("ex8 name:"+name);
        log.info("ex8 age:"+age);

    }
}
