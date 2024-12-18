package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.service.BoardService;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
// http://localhost:8080/board, 시작하겠다.
public class BoardController {
    private final BoardService boardService;
    // http://localhost:8080/board/list

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {
        // 서비스 이용해서, 데이터베이스 목록 페이징 처리해서 가져오기.
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    //등록 작업 1)등록화면, 2)로직 처리

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors: 유효성 에러 발생");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        Long bno = boardService.register(boardDTO);
//        글 정상 등록후, 화면에 result 값 전달, 1회용
        redirectAttributes.addFlashAttribute("result", "글작성 완료.");
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto", boardDTO);
    }

    @GetMapping("/update")
    public void update(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid BoardDTO boardDTO, BindingResult bindingResult, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes,
    String keyword2, String page2, String type2) {

        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        if(bindingResult.hasErrors()) {
            log.info("has errors: 유효성 에러 발생");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/update?bno="+boardDTO.getBno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;

        }
        boardService.update(boardDTO);
//        글 정상 등록후, 화면에 result 값 전달, 1회용
        redirectAttributes.addFlashAttribute("result", "수정된 bno:"+boardDTO.getBno());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/board/read?bno="+boardDTO.getBno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
    }

    @PostMapping("/delete")
    public String delete(Long bno, RedirectAttributes redirectAttributes,String keyword2, String page2, String type2) {
        boardService.delete(bno);
        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        redirectAttributes.addFlashAttribute("resultType", "delete");
        redirectAttributes.addFlashAttribute("result", "삭제되었습니다.");

        return "redirect:/board/list?"+"keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
    }



}
