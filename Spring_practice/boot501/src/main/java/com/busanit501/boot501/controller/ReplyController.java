package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.ReplyDTO;
import com.busanit501.boot501.service.ReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Tag(name = "댓글 등록 post 방식",
            description = "댓글 등록을 진행함, post 형식으로")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> register(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult
    ) throws BindException {
        log.info(" ReplyController replyDTO: ", replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String,Long> map = Map.of("rno",100L);
        return ResponseEntity.ok(map);
    }

    //댓글 목록 조회,
    @Tag(name="댓글 목록 조회", description = "댓글 목록 조회 RESTful")
    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO)
    {
        log.info(" ReplyController getList: bno={}", bno);
        PageResponseDTO<ReplyDTO> responseDTO = replyService.listWithReply(bno, pageRequestDTO);
        return responseDTO;    }

}





