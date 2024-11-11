package com.example.project2.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.project2.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void insertTest() {
    IntStream.rangeClosed(1, 300).forEach(i -> {
      Board board = Board.builder()
          .title("게시물" + i)
          .content("내용" + i)
          .writer("작가" + i)
          .build();
      boardRepository.save(board);
    });

  }

  @Test
  public void selectOneTest() {
    System.out.println(boardRepository.findById(15L).get());
  }

  @Test
  public void selectAllTest() {
    boardRepository.findAll().forEach(board -> System.out.println(board));
  }

  @Test
  public void updateTest() {
    Board board = boardRepository.findById(3L).get();
    board.setContent("내용수정");
    board.setWriter("작가수정");
    boardRepository.save(board);
  }

  @Test
  public void deleteTest() {
    boardRepository.deleteById(19L);
  }

  @Test
  public void deleteAllTest() {
    boardRepository.deleteAll();
  }

  // TODO: 쿼리 메소드 테스트
  @Test
  public void testTitleList() {
    // boardRepository.findByTitle("게시물20").forEach(b -> System.out.println(b));
    // boardRepository.findByTitleLike("게시물").forEach(b -> System.out.println(b));
    // boardRepository.findByTitleStartingWith("게시물").forEach(b ->
    // System.out.println(b));
    // boardRepository.findByWriterEndingWith("1").forEach(b ->
    // System.out.println(b));
    // boardRepository.findByWriterContaining("작가").forEach(b ->
    // System.out.println(b));
    // boardRepository.findByWriterContainingOrTitleContaining("작가", "게시물
    // ").forEach(b -> System.out.println(b));
    // boardRepository.findByTitleContainingAndIdGreaterThan("게시물", 10L).forEach(b
    // -> System.out.println(b));
    // boardRepository.findByIdGreaterThanOrderByIdDesc(10L).forEach(b ->
    // System.out.println(b));

    // TODO: 0:1 page 의미, pageSie : 한 페이지에 보여질 게시물 개수
    // Pageable pageable = PageRequest.of(0, 10);
    // boardRepository.findByIdGreaterThanOrderByIdDesc(0L, pageable).forEach(b ->
    // System.out.println(b));

    // boardRepository.findByWriterList("작가").forEach(b -> System.out.println(b));

    // boardRepository.findByTitle("게시물").forEach(b -> System.out.println(b));
    boardRepository.findByWriterContainingOrTitleContaining("작가", "게시물").forEach(b -> System.out.println(b));
  }

}
