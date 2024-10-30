package com.example.project2.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void insertTest() {
    IntStream.rangeClosed(1, 20).forEach(i -> {
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

}
