package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.*;
import com.busanit501.boot501.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
// http://localhost:8080/board, 시작하겠다.
public class BoardController {
    @Value("${com.busanit501.upload.path}")
    private String uploadPath;

    private final BoardService boardService;
    // http://localhost:8080/board/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {
        // 서비스 이용해서, 데이터베이스 목록 페이징 처리해서 가져오기.
        // 앞단 화면에서, 검색어:keyword 내용, 페이징 내용(page = 1) 담아서 전달.
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        // 교체 작업, 수정1
//        PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);
        // 교체 작업, 수정2, 게시글 + 댓글 갯수 + 첨부된 이미지들
        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);
        log.info("pageRequestDTO 의 getLink 조사 : " + pageRequestDTO.getLink());
        log.info("PageResponseDTO 의 responseDTO 조사 : " + responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    //등록 작업, 1) 등록화면 2) 로직처리
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    // 일반글로 만 받을 때, DTO 클래스로 받고 있는데,
    // 화면에서, -> 파일 이미지들을 문자열 형태로 , 각각 따로 보내고 있음.
    // 받을 때 타입을 변경.
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("BoardController register post 로직처리: ");
        log.info("BoardController register post  boardDTO : " + boardDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        //검사가 통과가 되고, 정상 입력
        Long bno = boardService.register(boardDTO);

        // 글 정상 등록후, 화면에 result 값을 전달하기.
        // 1회용 사용하기.
        redirectAttributes.addFlashAttribute("result", bno);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/board/list";

    }

    //권한별로 접근 지정. 관리자만 접근 가능.
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO,
                     Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto", boardDTO);
    }

    @GetMapping("/update")
    public void update(Long bno, PageRequestDTO pageRequestDTO,
                       Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid BoardDTO boardDTO,
                             BindingResult bindingResult,
                             PageRequestDTO pageRequestDTO,
                             String keyword2,String page2, String type2,
                             RedirectAttributes redirectAttributes) {
        log.info("BoardController updatePost post 로직처리: ");
        log.info("BoardController updatePost post  boardDTO : " + boardDTO);

        log.info("BoardController updatePost post  pageRequestDTO : " + pageRequestDTO);

        //키워드 한글 처리.
        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/update?bno="+boardDTO.getBno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
        }
        //검사가 통과가 되고, 정상 입력
        boardService.update(boardDTO);

        // 글 정상 등록후, 화면에 result 값을 전달하기.
        // 1회용 사용하기.
        redirectAttributes.addFlashAttribute("result", boardDTO.getBno());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/board/read?bno="+boardDTO.getBno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;

    }

    @PostMapping("/delete")
    public String delete(BoardDTO boardDTO,
                         String keyword2,String page2, String type2,
                         RedirectAttributes redirectAttributes) {
        Long bno = boardDTO.getBno();
        boardService.delete(bno);
        //키워드 한글 처리.
        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            // uploadController 가져와서 사용한다.
            removeFiles(fileNames);
        }
        redirectAttributes.addFlashAttribute("result", bno);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/board/list?"+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
    }


    // 물리서버 , 첨부 이미지 삭제 함수.
    public void removeFiles(List<String> fileNames) {
        for (String filename : fileNames) {
            Resource resource = new FileSystemResource(uploadPath+ File.separator+filename);
//            String resourceName = resource.getFilename();

            // 리턴 타입 Map 전달,
            Map<String,Boolean> resultMap = new HashMap<>();
            boolean deleteCheck = false;
            try {
                // 파일 삭제시, 이미지 파일일 경우, 원본 이미지와 , 썸네일 이미지 2개 있어서
                // 이미지 파일 인지 여부를 확인 후, 이미지 이면, 썸네일도 같이 제거해야함.
                String contentType = Files.probeContentType(resource.getFile().toPath());
                // 삭제 여부를 업데이트
                // 원본 파일을 제거하는 기능. (실제 물리 파일 삭제 )
                deleteCheck =resource.getFile().delete();

                if (contentType.startsWith("image")) {
                    // 썸네일 파일을 생성해서, 파일 클래스로 삭제를 진행.
                    // uploadPath : C:\\upload\springTest
                    // File.separator : C:\\upload\springTest\test1.jpg
                    File thumbFile = new File(uploadPath+File.separator,"s_"+ filename);
                    // 실제 물리 파일 삭제
                    thumbFile.delete();
                }
            }
            catch (Exception e) {
                log.error(e.getMessage());
            }
            resultMap.put("result", deleteCheck);
//            return resultMap;
        }
    }


}
