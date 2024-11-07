package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

  // TODO: 1) 쿼리 메소드 규칙에 따라 쿼리 생성하는 방식
  // where title = ?
  List<Board> findByTitle(String title);

  List<Board> findByTitleLike(String title);

}
