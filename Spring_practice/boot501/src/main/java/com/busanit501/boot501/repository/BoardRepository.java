package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
