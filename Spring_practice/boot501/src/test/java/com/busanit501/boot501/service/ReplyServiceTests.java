package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegisterReply() {
        // 더미 데이터 필요, 임시 DTO 생성.
        ReplyDTO replyDTO = ReplyDTO.builder()
                .bno(805L)
                .replyText("오늘 점심 뭐 먹지?")
                .replyer("LSH")
                .regDate(LocalDateTime.now())
                .build();

        Long bno = replyService.register(replyDTO);
        log.info("입력한 댓글 번호: " + bno.toString());
    }

    @Test
    public void testReadReply() {
        ReplyDTO replyDTO = replyService.readOne(5L);
        log.info("조회 댓글: " + replyDTO.toString());
    }

    @Test
    public void testUpdateReply() {
        // 더미 데이터 필요, 임시 DTO 생성.
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(5L)
                .bno(805L)
                .replyText("수정Text2?")
                .replyer("LSH")
                .regDate(LocalDateTime.now())
                .build();

        replyService.update(replyDTO);

    }

    @Test
    public void testDeleteReply() {
        replyService.delete(5L);
    }

    @Test
    public void testAllReply() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<ReplyDTO> result = replyService.listWithReply(805L,pageRequestDTO);
        log.info("result" + result);
    }
}
