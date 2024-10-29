package com.example.project2.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Memo;

@SpringBootTest // TODO: @SpringBootTest 명시
public class MemoRepositoryTest {

  @Autowired // 스프링 컨테이너가 관리하고있는 객체를 주입해줘
  private MemoRepository memoRepository; // TODO: interface 와 추상클래스는 new 못함 => serviceImpl 같은 구현클래스에서

  // C(insert)
  @Test // TODO: 메소드 위에도 @Test 붙여야 함
  public void insertTest() {

    LongStream.rangeClosed(1, 10).forEach(i -> { // 10번 insert

      Memo memo = Memo.builder().memoText("Memo Test....." + i).build(); // 1 ~ i 번까지
      System.out.println(memoRepository.save(memo)); // 10번 save
    });
  }

  // R(Read)- 하나 조회 PK
  @Test // TODO: 메소드 위에도 @Test 붙여야 함
  public void selectOneTest() {
    Optional<Memo> result = memoRepository.findById(5L);

    Memo memo = result.get();
    System.out.println(memo);
  }

  // R(Read) - 전체 조회
  @Test // TODO: 메소드 위에도 @Test 붙여야 함
  public void selectAllTest() {
    List<Memo> list = memoRepository.findAll();

    for (Memo memo : list) {
      System.out.println(memo);
    }
  }

  // U(Update) - 조건부 업데이트
  @Test // TODO: 메소드 위에도 @Test 붙여야 함
  public void updateTest() {
    Optional<Memo> result = memoRepository.findById(5L);

    result.ifPresent(memo -> {
      memo.setMemoText("Update Title...");
      System.out.println(memoRepository.save(memo));
    });
  }

  // D (Delete) - 하나 삭제
  @Test // TODO: 메소드 위에도 @Test 붙여야 함
  public void deleteTest() {
    // Optional<Memo> result = memoRepository.findById(10L); // 10번 삭제

    // result.ifPresent(memo -> {
    // memoRepository.delete(memo);
    // });
    memoRepository.deleteById(3L); // 3번 삭제
  }
}
