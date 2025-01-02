package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.BoardImage;
import com.busanit501.boot501.domain.Reply;
import com.busanit501.boot501.dto.BoardListAllDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Test
    public void testInsert() {
        IntStream.range(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("샘플 제목:"+i)
                    .content("샘플 내용:"+i)
                    .writer("샘플 작성자:"+i).build();
           Board result = boardRepository.save(board);
           log.info("추가된 bno 번호 : "+result);
        });
    }

    @Test
    public void testSelectOne(){
        Long bno = 99L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info("하나 조회:"+board);
    }

    @Test
    public void testSelectAll(){
        List<Board> result = boardRepository.findAll();
        for (Board board : result) {
            log.info(board);
        }
    }

    @Test
    public void testUpdate(){
        Long bno = 99L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.changeTitleContent("변경제목","변경내용");
        boardRepository.save(board);
    }

    @Test
    public void testDelete(){
        Long bno = 99L;
        boardRepository.deleteById(bno);

    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());
    }

    @Test
    public void testQueryString(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByTitleContainingOrderByBnoDesc("3",pageable);
        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());

    }

    @Test
    public void testQueryAnotation(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByKeyword("3",pageable);
        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());
    }

    @Test
    public void testQuerydsl(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        boardRepository.search(pageable);
//        Page<Board> result = boardRepository.findByKeyword("3",pageable);

//        log.info("총 개수:"+result.getTotalElements());
//        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
//        log.info("페이징 된 결과물 10개:"+result.getContent());
//        log.info("현재 페이지 번호:"+result.getNumber());
//        log.info("크기:"+result.getSize());
    }

    @Test
    public void testQuerydsl2(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        //전달할 준비물
        //1) 검색어, 2)검색유형
        String keyword = "3";
        String[] types = {"t","w","c"};
        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);

        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());
        log.info("다음여부:"+result.hasNext());
        log.info("이전여부:"+result.hasPrevious());
    }

    //부모 게시글 삭제시, 자식 테이블 첨부 이미지 삭제되는 부분 확인
    //순서1 생성
    @Test
    public void testInsertWithImages() {
        // 더미 데이터 만들기.
        // 부모 더미 데이터 board , 0x100
        Board board = Board.builder()
                .title("오늘 점심 뭐 먹죠? 생각중. 한식쟁이 밥이 생각남")
                .content("국밥, 된장찌개, 비빔밥")
                .writer("이상용")
                .build();

        //더미 데이터, 임의 사진을 추가 해보기.
        for (int i = 0; i < 3; i++) {
            String uuid = UUID.randomUUID().toString();
            String fileName = "SampleImageFileName";
            board.addImage(uuid, fileName + i + ".png");
        }
        boardRepository.save(board);

    }

    //순서2 조회 -> 지연 로딩, 한번에 다 조인해서 조회
    @Test
    @Transactional
    public void testReadWithImages() {
        //더미 데이터 2개, 게시글 1, 2번
//        Optional<Board> result = boardRepository.findById(3L);
        Optional<Board> result = boardRepository.findByIdWithImages(3L);
        Board board = result.orElseThrow();

        // 보드 출력해보기. 1차 캐시 테이블에서, 더티 체킹, select
        // 단위 테스트 할 때, board 조회 할 때 세션이 하나 필요하고
        // 단위 테스트 할 때, boardImage 이미지 조회 할 때 세션이 하나 더 필요하고
        // 단위 테스트 이용시에는 디비 접근 세션을 하나만 이용해서, 오류가 발생함.

        log.info("BoardRepositoryTests board 확인  : " + board);
        log.info("========================================== ");
        log.info("BoardRepositoryTests board.getImageSet() 확인2  : " + board.getImageSet());
        // 1차 오류 발생함.
        for (BoardImage boardImage : board.getImageSet()) {
            log.info("BoardRepositoryTests board.getImageSet() 확인32  : " + boardImage);
        }

    }

    // 순서3, 수정, -> 고아객체 만들기.
    @Transactional
    //단위 테스트에서, 수정을 반영하기.
    @Commit
    @Test
    public void testUpdateImages() {
        Optional<Board> result = boardRepository.findByIdWithImages(3L);
        Board board = result.orElseThrow();

        // 이미지 수정시, 기존 이미지를 전부 다 삭제 후 새로 추가하기.
        board.clearImages();

        // 새 첨부 이미지 추가하기.
        for (int i = 0; i < 2; i++) {
            String uuid = UUID.randomUUID().toString();
            String fileName = "SampleImageFileName_수정233333333";
            board.addImage(uuid, fileName + i + ".png");
        }
        boardRepository.save(board);
    }


    //순서4 부모 게시글 삭제시, 자식 테이블 첨부 이미지 삭제되는 부분 확인
    @Test
    @Transactional
    @Commit //DML 적용되기 위한 설정
    public void removeAll(){
        Long bno =3L;
        replyRepository.deleteByBoard_Bno(bno);
        boardRepository.deleteById(bno);
    }

    // N + 1 문제, 더미 데이터 추가.
    @Transactional
    @Commit
    @Test
    public void testInsertAll() {
        for(int i = 1; i <=100 ; i++){
            Board board = Board.builder()
                    .title("샘플 데이터 " + i)
                    .content("샘플 제목 " + i)
                    .writer("이상용"+i)
                    .build();

            for (int j=0; j<3; j++){
                if(i % 5 ==0) {
                    // 5번째 씩 , 첨부 이미지 추가 안하기.
                    continue;
                }
                // 첨부 이미지 3장씩 더미데이터
                String uuid = UUID.randomUUID().toString();
                String fileName = "샘플 이미지";
                board.addImage(uuid,fileName+j+".png");


            }
            // 게시글 작성 후 ,
            boardRepository.save(board);
            // 댓글 달기.
            for (int j=0; j<3; j++) {
                Reply reply = Reply.builder()
                        .board(board)
                        .replyText("샘플 댓글" + j)
                        .replyer("이상용")
                        .build();
                replyRepository.save(reply);
            }
        }
    }

    // N+1 문제 조회 확인
    @Transactional
    @Test
    public void testSearchWithAll() {
        Pageable pageable = PageRequest.of(0,10,
                Sort.by("bno").descending());
        boardRepository.searchWithAll(null,null,pageable);
    }

    @Transactional
    @Test
    public void testSearchWithAll2() {
        Pageable pageable = PageRequest.of(0,10,
                Sort.by("bno").descending());

        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null,null,pageable);
        // 전체 갯수
        log.info("test, result.getTotalElements() 확인1 : " + result.getTotalElements());
        // 각 목록의 요소 확인.
        result.getContent().forEach(boardListAllDTO -> {
            log.info("boardListAllDTO, 각 요소 확인2 : " + boardListAllDTO);
        });
    }

    @Transactional
    @Test
    public void testSearchWithAll3() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending());

        String keyword = "3";
        String[] types = {"t", "w", "c"};
        //
        Page<BoardListAllDTO> result = boardRepository.searchWithAll(types, keyword, pageable);
        // 전체 갯수
        log.info("test, result.getTotalElements() 확인1 : " + result.getTotalElements());
        // 각 목록의 요소 확인.
        result.getContent().forEach(boardListAllDTO -> {
            log.info("boardListAllDTO, 각 요소 확인2 : " + boardListAllDTO);
        });
    }
}
