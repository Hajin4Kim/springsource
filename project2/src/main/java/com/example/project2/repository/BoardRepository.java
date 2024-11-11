package com.example.project2.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project2.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

  // // TODO: 1) 쿼리 메소드 규칙에 따라 쿼리 생성하는 방식
  // // spring data JPA 쿼리 메소드 방법 사용
  // // where title = ?
  // List<Board> findByTitle(String title);

  // // where title like = ?
  // List<Board> findByTitleLike(String title);

  // // where title like '%title'
  // List<Board> findByTitleStartingWith(String title);

  // // where writer like '작가%'
  // List<Board> findByWriterEndingWith(String writer);

  // // where writer like '%작가%'
  // List<Board> findByWriterContaining(String writer);

  // // where writer like '%작가%' or title like '%title%;'
  // List<Board> findByWriterContainingOrTitleContaining(String writer, String
  // title);

  // // where title like '%title%' and id >10
  // List<Board> findByTitleContainingAndIdGreaterThan(String title, Long id);

  // // WHERE id > 10 order by desc
  // // 전체 게시물 조회는 20L(가장 최근 만들어진게시물)
  // List<Board> findByIdGreaterThanOrderByIdDesc(Long id);

  // List<Board> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);

  // 실 SQL은 아님
  // select * from board b where b.writer like '%작가%' and b.id > 0 order by b.id
  // desc;
  // TABLE명이 아닌, ENTITY명 Board
  // @Query("SELECT b FROM Board b WHERE b.writer LIKE %:writer% AND b.id > 0
  // ORDER BY b.id DESC")
  // TODO: 2)번째 방법 ; @Query 어노테이션 사용
  @Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% AND b.id > 0 ORDER BY b.id DESC")
  List<Board> findByWriterList(String writer); // parameter writer 를 표현하는 법 %:writer%

  // @Query("SELECT b FROM Board b WHERE b.title = :title")
  // @Query("SELECT b FROM Board b WHERE b.title LIKE %:title%")
  @Query("SELECT b FROM Board b WHERE b.title LIKE %?1%")
  List<Board> findByTitle(String title);

  @Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% OR  b.title LIKE %?2%")
  List<Board> findByWriterContainingOrTitleContaining(String writer, String title);
}
