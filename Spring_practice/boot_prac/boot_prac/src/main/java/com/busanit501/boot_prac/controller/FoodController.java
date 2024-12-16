package com.busanit501.boot_prac.controller;


import com.busanit501.boot_prac.domain.Food;
import com.busanit501.boot_prac.dto.FoodDTO;
import com.busanit501.boot_prac.dto.PageRequestDTO;
import com.busanit501.boot_prac.dto.PageResponseDTO;
import com.busanit501.boot_prac.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {
        PageResponseDTO<FoodDTO> responseDTO = foodService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }
    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String registerPost(@Valid FoodDTO foodDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors: 유효성 에러 발생");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        Long fno = foodService.register(foodDTO);
        redirectAttributes.addFlashAttribute("result", "글작성 완료.");
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/food/list";
    }

    @GetMapping("/read")
    public void read(Long fno, PageRequestDTO pageRequestDTO, Model model) {
        FoodDTO foodDTO = foodService.readOne(fno);
        model.addAttribute("dto", foodDTO);
    }

    @GetMapping("/update")
    public void update(Long fno, PageRequestDTO pageRequestDTO, Model model) {
        FoodDTO foodDTO = foodService.readOne(fno);
        model.addAttribute("dto", foodDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid FoodDTO foodDTO, BindingResult bindingResult, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes,
    String keyword2, String page2, String type2) {
        if(bindingResult.hasErrors()) {
            log.info("has errors: 유효성 에러 발생");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?fno="+foodDTO.getFno()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;

        }
        foodService.update(foodDTO);
//        글 정상 등록후, 화면에 result 값 전달, 1회용
        redirectAttributes.addFlashAttribute("result", "수정된 fno:"+foodDTO.getFno());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/food/read?fno="+foodDTO.getFno()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
    }

    @PostMapping("/delete")
    public String delete(Long fno, RedirectAttributes redirectAttributes,String keyword2, String page2, String type2) {
        foodService.delete(fno);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        redirectAttributes.addFlashAttribute("result", "삭제되었습니다.");

        return "redirect:/food/list?"+"keyword="+keyword2+"&page="+page2+"&type="+type2;
    }



}
