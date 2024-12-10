package com.busanit501.boot_prac.repository;

import com.busanit501.boot_prac.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
