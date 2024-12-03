package com.busanit501.spring_prac.controller;

import com.busanit501.spring_prac.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {
    @RequestMapping("/list")
    public void list() {
        log.info("list:화면제공은 해당 메서드 명으로 제공");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("register:화면제공은 해당 메서드 명으로 제공");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerPost(TodoDTO todoDTO) {
        log.info("register Post 로직 처리");
        log.info("TodoController register post todoDTO:"+todoDTO);
    }
}
