package com.example.mart.repository;

import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.Member;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.MemberRepository;

@SpringBootTest
public class LockerRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private LockerRepository lockerRepository;

  @Test
  public void testLockerInsert() {
    // Caused by: .support.BeanDefinitionOverrideException: Invalid bean definition
    // with name
    LongStream.rangeClosed(1, 4).forEach(i -> {
      Member member = Member.builder().id(i).build();

      // 관계맺은거 가져오기 .member(member)
      Locker locker = Locker.builder().name("locker" + i).member(member).build();
      lockerRepository.save(locker);
    });

    // member 4
    LongStream.rangeClosed(1, 4).forEach(i -> {
      Locker locker = Locker.builder().id(i).build();

      // 관계맺은거 가져오기 .locker(locker)
      Member member = Member.builder().name("member" + i).locker(locker).build();
      memberRepository.save(member);
    });

  }
}
