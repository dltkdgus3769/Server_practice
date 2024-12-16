package com.busanit501.boot_prac.repository.search;

import com.busanit501.boot_prac.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FoodSearch {
    Page<Food> search(Pageable pageable);


    Page<Food> searchAll(String[] types, String keyword, Pageable pageable);

}
