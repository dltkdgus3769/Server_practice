package com.busanit501.boot501.repository.search;


import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.QBoard;
import com.busanit501.boot501.domain.QReply;
import com.busanit501.boot501.dto.BoardImageDTO;
import com.busanit501.boot501.dto.BoardListAllDTO;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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

    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        JPQLQuery<Board> boardJPQLQuery = from(board);// select * from board
        boardJPQLQuery.leftJoin(reply).on(reply.board.bno.eq(board.bno));

        if (types != null && types.length > 0 && keyword != null) {
            // 여러 조건을 하나의 객체에 담기.
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
                } // switch
            }// end for
            // where 조건을 적용해보기.
            boardJPQLQuery.where(booleanBuilder);
        } //end if
        // bno >0
        boardJPQLQuery.where(board.bno.gt(0L));

        boardJPQLQuery.groupBy(board);
        this.getQuerydsl().applyPagination(pageable, boardJPQLQuery);



        JPQLQuery<Tuple> tupleJPQLQuery = boardJPQLQuery.select(
          // 게시글과, 댓글 갯수 조회 결과
                board,reply.countDistinct()
        );
        List<Tuple> tupleList = tupleJPQLQuery.fetch();
        List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple ->{
            Board board1=  (Board)tuple.get(board);
            long replyCount = tuple.get(1,Long.class);
            BoardListAllDTO dto = BoardListAllDTO.builder()
                    .bno(board1.getBno())
                    .title(board1.getTitle())
                    .writer(board1.getWriter())
                    .regDate(board1.getRegDate())
                    .replyCount(replyCount).build();

            //첨부이미지 추가, 추가1
            List<BoardImageDTO> imageDTO = board1.getImageSet().stream().sorted().map(boardImage ->
                BoardImageDTO.builder()
                        .uuid(boardImage.getUuid())
                        .fileName(boardImage.getFileName())
                        .ord(boardImage.getOrd())
                        .build()
            ).collect(Collectors.toList());
            dto.setBoardImages(imageDTO);
            return dto;
        }).collect(Collectors.toList());

        long totalCount = boardJPQLQuery.fetchCount();
        Page<BoardListAllDTO> page = new PageImpl<>(dtoList, pageable, totalCount);
        return page;
    }

//    @Override
//    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
//        QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//        JPQLQuery<Board> boardJPQLQueryuery = from(board);// select * from board
//        boardJPQLQueryuery.leftJoin(reply).on(reply.board.bno.eq(board.bno));
//        this.getQuerydsl().applyPagination(pageable, boardJPQLQueryuery);
//
//        // 페이징 된 데이터 가져오기
//        List<Board> boardList = boardJPQLQueryuery.fetch();
//
//        boardList.forEach(board1 -> {
//            log.info("board1:" + board1.getBno());
//            log.info("board1:" + board1.getImageSet());
//        });
//
//        return null;
//    }


}
