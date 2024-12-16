package com.busanit501.boot_prac.todo;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class TodoController {



    @GetMapping("/todo/list")
    public void list(Model model) {
        List<String> strList = IntStream.range(1,10).mapToObj(i->"임시 데이터"+i).collect(Collectors.toList());
        model.addAttribute("strList", strList);

        Map<String,String> map = new HashMap<>();
        map.put("a","aaa");
        map.put("b","bbb");
        model.addAttribute("map", map);
    }

    @GetMapping("/todo/register")
    public void register(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }


}
