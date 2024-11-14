package com.example.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

@SpringBootTest
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ReplyRepository replyRepository;

  @Test
  public void testInsertMember() {
    // 30개
    IntStream.rangeClosed(1, 30).forEach(i -> {
      Member member = Member.builder()
          .email("user" + i + "@gmail.com")
          .name("user" + i)
          .password("1111")
          .build();
      memberRepository.save(member);
    });
  }

  @Test
  public void testInsertBoard() {
    // 100개
    IntStream.rangeClosed(1, 100).forEach(i -> {
      int num = (int) (Math.random() * 30) + 1;
      Member member = Member.builder().email("user" + num + "@gmail.com").build();

      Board board = Board.builder()
          .title("title...." + i)
          .content("content...." + i)
          .writer(member)
          .build();
      boardRepository.save(board);
    });
  }

  @Test
  public void testInsertReply() {
    // 100개
    IntStream.rangeClosed(1, 100).forEach(i -> {

      long bno = (long) (Math.random() * 100) + 1;
      Board board = Board.builder().bno(bno).build();

      Reply reply = Reply.builder()
          .text("reply...." + i)
          .replyer("guest" + i)
          .board(board)
          .build();
      replyRepository.save(reply);
    });
  }
}
