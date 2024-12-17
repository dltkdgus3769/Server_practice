package com.busanit501.boot501.repository.search;


import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.QBoard;
import com.busanit501.boot501.domain.QReply;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    //부모 클래스 초기화, 사용하는 엔티티 클래스 설정.
    //Board
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search(Pageable pageable) {
        //예시,
        QBoard board = QBoard.board; // Q 도메인 객체, 엔티티 클래스(= 테이블)
        JPQLQuery<Board> query = from(board); // select * from board 한 결과와 비슷함.
        //select * from board 작성한 내용을 query 객체 형식으로 만듦.
        // 다양한 쿼리 조건을 이용할수 있음.
        // 예, where, groupby, join , pagination
        query.where(board.title.contains("3"));
        // =================================.,조건1

        // 제목, 작성자 검색 조건 추가,
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(board.title.contains("3"));// "3" 제목 임시
        booleanBuilder.or(board.content.contains("7"));// "3" 제목 임시
        // query, 해당 조건을 적용함.
        query.where(booleanBuilder);
        // 방법2, 추가 조건으로, bno 가 0보다 초과 하는 조건.
        query.where(board.bno.gt(0L));

        // =================================.,조건3

        // 페이징 조건 추가하기. qeury에 페이징 조건을 추가한 상황
        this.getQuerydsl().applyPagination(pageable, query);
        // =================================.,조건2

        // 해당 조건의 데이터를 가져오기,
        List<Board> list = query.fetch();
        // 해당 조건에 맞는 데이터의 갯수 조회.
        long total = query.fetchCount();
        //

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board); //select * from board
        if(types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type : types) {
                switch(type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        //========================================================
        //위 조건으로 검색, 1)페이징 결과물, 2)페이징된 전체갯수
        // 해당 조건의 데이터를 가져오기,
        List<Board> list = query.fetch();
        // 해당 조건에 맞는 데이터의 갯수 조회.
        long total = query.fetchCount();

        Page<Board> result = new PageImpl<Board>(list, pageable, total);
        return result;
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        JPQLQuery<Board> query = from(board);// select * from board
        query.leftJoin(reply).on(reply.board.bno.eq(board.bno));
        query.groupBy(board);

        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.bno.gt(0L));

        JPQLQuery<BoardListReplyCountDTO> dtoQuery =
                query.select(Projections.bean(BoardListReplyCountDTO.class,
                        board.bno,
                        board.title,
                        board.content,
                        board.writer,
                        board.regDate,
                        reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);


        List<BoardListReplyCountDTO> list = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();

        Page<BoardListReplyCountDTO> result = new PageImpl<BoardListReplyCountDTO>(list, pageable, total);

        return result;
    }


}
