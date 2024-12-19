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
        Long rno = replyService.register(replyDTO);
        Map<String,Long> map = Map.of("rno",rno);
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

    //댓글 하나 조회,
    @Tag(name = "댓글 하나 조회",description = "댓글 하나 조회 RESTful get방식")
    @GetMapping(value ="/{rno}")
    public ReplyDTO getRead(@PathVariable("rno") Long rno)
    {
        log.info(" ReplyController getRead: rno={}", rno);
        ReplyDTO replyDTO = replyService.readOne(rno);
        return replyDTO;
    }

    //댓글 수정,
    @Tag(name = "댓글 수정 로직처리",description = "댓글 수정 로직처리 RESTful get방식")
    @PutMapping(value ="/{rno}")
    public Map<String,Long> updateReply(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult,
            @PathVariable("rno") Long rno) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        log.info(" ReplyController updateReply: rno={}", rno);
        replyService.update(replyDTO);
        Map<String,Long> map = Map.of("rno",rno);
        return map;
    }

    //댓글 삭제,
    @Tag(name = "댓글 삭제 로직처리",description = "댓글 삭제 로직처리 RESTful get방식")
    @DeleteMapping(value ="/{rno}")
    public Map<String,Long> deleteReply(@PathVariable("rno") Long rno){

        log.info(" ReplyController deleteReply: rno={}", rno);
        replyService.delete(rno);
        Map<String,Long> map = Map.of("rno",rno);
        return map;
    }



}





