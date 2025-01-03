package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
//public interface BoardRepository extends JpaRepository<Board, Long>{
    //쿼리스트링, 방법1
    Page<Board> findByTitleContainingOrderByBnoDesc(String title, Pageable pageable);

    //@Query, 방법2 , JPQL 문법으로 작성, 모든 디비에 적용 됨.
    @Query("select b from Board b where b.title like concat('%',:keyword,'%')")
    Page<Board> findByKeyword(String keyword, Pageable pageable);

    //Querydsl 도구 이용, 방법3,
    // BoardSearch 인터페이스 구현, 이 인터페이스를 구현한 클래스에서 문법 사용.
    // BoardSearchImpl 구현한 클래스의 이름. 구현체,

    // 방법2,에서 JPQL, 디비에 상관없이 작성도 되지만,
    // 반대로, 특정 디비의 문법으로 만 작성도 가능.
    // nativeQuery = true
    @Query(value = "select now()" , nativeQuery = true)
    String now();

    // 조회시, 특정의 속성을 같이 조회를 함.
    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Board b where b.bno=:bno")
    Optional<Board> findByIdWithImages(Long bno);

}
