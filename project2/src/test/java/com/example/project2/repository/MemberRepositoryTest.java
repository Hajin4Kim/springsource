package com.example.project2.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import com.example.project2.entity.Member;
import com.example.project2.entity.constant.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void insertTest() {
    // 하나 삽입
    // Member member = Member.builder()
    // .id("user1")
    // .userName("user1")
    // .age(20)
    // .roleType(RoleType.USER)
    // .build();
    // memberRepository.save(member);

    // TODO: IntStream 활용하여 10번 반복 삽입
    IntStream.rangeClosed(2, 10)
        .forEach(i -> {
          Member member = Member.builder()
              .id("user" + i)
              .userName("user" + i)
              .age(20 + i)
              .roleType(RoleType.USER)
              .createdDate(LocalDateTime.now())
              .build();
          memberRepository.save(member);
        });
  }

  @Test
  public void selectOneTest() {
    Member member = memberRepository.findById("user7").get();
    System.out.println(member);
  }

  @Test
  public void selectAllTest() {
    memberRepository.findAll().forEach(member -> System.out.println(member));
  }

  @Test
  public void updateTest() {
    // // save() : insert 또는 update
    // Member member = Member.builder()
    // .id("user8")
    // .userName("user8")
    // .age(28)
    // .roleType(RoleType.ADMIN)
    // .lastModifiedDate(LocalDateTime.now())
    // .build();
    // memberRepository.save(member);
    // TODO: 위 방법은 설정하지 않은 col 값이 Null 이 되어버리므로 이 .get() .set() 방법을 더 잘씀
    Member member = memberRepository.findById("user9").get();
    member.setRoleType(RoleType.ADMIN);
    member.setLastModifiedDate(LocalDateTime.now());
    memberRepository.save(member);
  }

  @Test
  public void deleteTest() {
    // memberRepository.delete(null);
    memberRepository.deleteById("user10");
  }
}
