package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 기본적인 crud , 쿼리 스트링으로 가능함.

    // 댓글 목록 조회 해보기.
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);

    void deleteByBoard_Bno(Long bno);

    //부모 게시글에 대한, 댓글의 목록 조회
    List<Reply> findByBoardBno(Long bno);

}
