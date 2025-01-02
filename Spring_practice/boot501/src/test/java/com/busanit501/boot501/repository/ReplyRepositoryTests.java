package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.Reply;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 댓글을 작성 하려면, 부모 게시글 번호가 필요,
        // 각자 데이터베이스에 따라서, 다르므로 꼭 확인하고, 작업.
        Long bno = 3L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
                .board(board)
                .replyText("샘플 댓글")
                .replyer("샘플 작성자")
                .build();

        replyRepository.save(reply);
    }

//    @Transactional
//    @Test
//    public void testSelect() {
//        Long bno = 2L;
//        // 페이징 조건, 준비물 준비
//        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());
//
//        //
//        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
//        // result 안에, 페이징 조건의 준비물이 다 있음.
//        // 1) 전체 갯수, 2) 페이지 3) 페이지당 크기 4) 페이징 처리된 목록 요소
//        result.getContent().forEach(reply -> {
//            log.info(reply);
//        });
//    }
// 게시글 표시에 댓글 갯수 추가해서 조회하기.
    @Test
    public void testSelectWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending());

        // 전달할 준비물
        // 1) 검색어, 2) 검색 유형
        String keyword = "123";
        String[] types = {"t","w","c"};

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types,keyword,pageable);

        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
        log.info("result.hasNext() 다음  :" +result.hasNext());
        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
    }

    @Test
    @Transactional
    public void testSelectWithReply() {

        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfBoard(805L, pageable);

        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
        log.info("result.hasNext() 다음  :" +result.hasNext());
        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
    }

    //하나조회
    @Test
    @Transactional
    public void testSelectOneReply(){

        Optional<Reply> result = replyRepository.findById(2040L);
        Reply reply=result.orElseThrow();
        log.info("하나조회 결과:"+reply);
    }

    @Test
    @Transactional
    public void testSelectReplyWithBoardBno(){
        Long bno=103L;
        List<Reply> replyList = replyRepository.findByBoardBno(bno);
        replyList.forEach(reply -> log.info("게시글 조회 : "+reply));

    }

}






