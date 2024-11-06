package com.example.memo.repository;

import java.util.List;
import java.util.stream.IntStream;
// import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

  @Autowired
  private MemoRepository memoRepository;

  @Test
  public void testMemoInsert() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Memo memo = Memo.builder().memoText("memo text" + i).build();
      memoRepository.save(memo);
    });
  }

  @Test
  public void testMemoRead() {
    // 특정 메모 하나 읽어오기 26L
    Memo memo = memoRepository.findById(26L).get();
    System.out.println(memo);
    // 전체 메모 읽어오기
    List<Memo> list = memoRepository.findAll();
    list.forEach(m -> System.out.println(m));
  }

  @Test
  public void testMemoUpdate() {
    // 27번 메모 내용 수정
    Memo memo = memoRepository.findById(27L).get();
    memo.setMemoText("modify update test");
    memoRepository.save(memo);
  }

  @Test
  public void testMemoDelete() {
    // 마지막 메모 삭제
    memoRepository.deleteById(30L);
  }

}
