package com.busanit501.spring_prac.controller;


import com.busanit501.spring_prac.dto.FoodDTO;
import com.busanit501.spring_prac.dto.PageRequestDTO;
import com.busanit501.spring_prac.dto.PageResponseDTO;
import com.busanit501.spring_prac.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/food")
@Log4j2
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @RequestMapping("/list")
//    public void list(Model model) {
    public String list(@Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함.");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/list";
        }
        PageResponseDTO<FoodDTO> pageResponseDTO = foodService.selectList(pageRequestDTO);
        log.info("FoodController list 데이터 유무 확인 :" + pageResponseDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/food/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("register:화면제공은 해당 메서드 명으로 제공");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + foodDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        //검사가 통과가 되고, 정상 입력
        foodService.register(foodDTO);

        return "redirect:/food/list";
    }

    //상세조회,
    @RequestMapping("/read")
    public String read(Long fno, Model model, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno", fno);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/food/read";
        }


        log.info("FoodController read :");
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController read 데이터 유무 확인:" + foodDTO);
        model.addAttribute("foodDTO", foodDTO);
        return "/food/read";
    }


    @RequestMapping("/update")
    public String update(Long fno, Model model,@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("FoodController update :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno", fno);
//            redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
//            redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
            return "redirect:/food/update";
        }
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController update 데이터 유무 확인 :" + foodDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("foodDTO", foodDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/food/update";
    }

    @PostMapping("/update")
    public String updateLogic(@Valid FoodDTO foodDTO, BindingResult bindingResult, @Valid PageRequestDTO pageRequestDTO, BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes) {

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.FoodDTO");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno",foodDTO.getFno());
            redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
            return "redirect:/food/update?"+pageRequestDTO.getLink();
        }
        if (pageBindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.PageRequestDTO");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors2", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno",foodDTO.getFno());
            redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
            return "redirect:/food/update?"+pageRequestDTO.getLink();
        }

        // 수정하는 로직 필요함.
        // 주의사항, 체크박스의 값의 문자열 on 전달 받습니다.
        log.info("foodDTO확인 finished의 변환 여부 확인. : " + foodDTO);

        foodService.update(foodDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/food/list?"+pageRequestDTO.getLink();
    }

    //삭제
    @PostMapping("/delete")
    public String delete(Long fno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        foodService.delete(fno);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/food/list?"+pageRequestDTO.getLink();
    }


    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1:" + name);
        log.info("ex1:" + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "LSH") String name,
                    @RequestParam(name = "age", defaultValue = "30") int age) {
        log.info("ex2:" + name);
        log.info("ex2:" + age);
    }


    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) {
        log.info("ex3:" + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("ex4:");
        model.addAttribute("msg", " <script>\n" +
                "                    alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" +
                "                </script>");
    }

    @GetMapping("/ex5")
    //localhost:8080/food/ex5?title=lsh&writer=이상현
    public void ex5(FoodDTO foodDTO, Model model) {
        log.info("ex5:" + foodDTO);

    }

    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes) {
        log.info("ex6:");
        redirectAttributes.addAttribute("msg", "test data");
        redirectAttributes.addFlashAttribute("msg2", "일회용 데이터");

        return "redirect:/ex7";

    }

    @GetMapping("/ex7")
    public void ex7(String msg, Model model) {
        log.info("ex7:");
        model.addAttribute("msg", msg);
    }
}
