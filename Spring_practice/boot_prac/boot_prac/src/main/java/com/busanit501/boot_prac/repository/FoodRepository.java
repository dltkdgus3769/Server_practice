package com.busanit501.boot_prac.repository;

import com.busanit501.boot_prac.domain.Food;
import com.busanit501.boot_prac.repository.search.FoodSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodSearch {
    //방법1 쿼리스트링
    Page<Food> findByTitleContainingOrderByFnoDesc(String title, Pageable pageable);

    //방법2 @Query 사용
    @Query("select b from Food b where b.title like concat('%',:keyword,'%')")
    Page<Food> findByKeyword(String keyword, Pageable pageable);
}
