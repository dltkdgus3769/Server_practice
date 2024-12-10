package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        List<String> strList = IntStream.range(1,10).mapToObj(i->"임시 데이터"+i).collect(Collectors.toList());
        model.addAttribute("strList", strList);

        Map<String,String> map = new HashMap<>();
        map.put("a","aaa");
        map.put("b","bbb");
        model.addAttribute("map", map);

        SampleDTO sampleDTO = SampleDTO.builder()
                .p1("테스트 p1")
                .p1("테스트 p2")
                .p1("테스트 p3")
                .p1("테스트 p4").build();
        model.addAttribute("sampleDTO", sampleDTO);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }

}
