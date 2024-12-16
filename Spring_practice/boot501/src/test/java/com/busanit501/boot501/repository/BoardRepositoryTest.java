package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

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
}
