package com.example.mart.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.SportsMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LockerRepositoryTest {

  @Autowired // Caused by: .support.BeanDefinitionOverrideException: Invalid bean definition
  private SportsMemberRepository memberRepository;

  @Autowired
  private LockerRepository lockerRepository;

  @Test
  public void testLockerInsert() {

    // with name
    LongStream.rangeClosed(5, 8).forEach(i -> {
      // SportsMember member = SportsMember.builder().id(i).build();

      Locker locker = Locker.builder().name("locker" + i).build();
      lockerRepository.save(locker);
    });

    // member 4
    LongStream.rangeClosed(5, 8).forEach(i -> {
      Locker locker = Locker.builder().id(i).build();

      // 관계맺은거 가져오기 .locker(locker)
      SportsMember member = SportsMember.builder().name("member" + i).locker(locker).build();
      memberRepository.save(member);
    });

  }

  // TODO: 수정시간 Auditing 들어가는지 @LastModifiedDate
  // @OneToOne -> FetchType.EAGER(즉시로딩)
  @Test
  public void testMemberUpdate() {
    SportsMember sportsMember = memberRepository.findById(5L).get();
    sportsMember.setName("test5");
    memberRepository.save(sportsMember);
  }

  @Transactional // TODO: FetchType.LAZY 인 경우에도 이거 붙이면 OK
  @Test
  public void testMemberRead() {
    // 회원 조회(+ Locker 조회) //TODO: 즉시로딩 이기 때문에 가능함
    SportsMember sportsMember = memberRepository.findById(3L).get();
    System.out.println(sportsMember);

    // 객체 그래프 탐색 (Locker 조회) //TODO: 즉시로딩 이기 때문에 가능함
    System.out.println(sportsMember.getLocker());

    // 전체sportsMember 조회
    memberRepository.findAll().forEach(member -> {
      System.out.println(member.getLocker()); // + locker 조회
    });
  }

  @Test
  public void testLockerRead() {
    // 전체locker 조회(+회원조회)
    lockerRepository.findAll().forEach(locker -> {
      System.out.println(locker);
      System.out.println(locker.getSportsMember()); // + member 조회
    });
  }
}
