package com.example.movie.respository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testInsert() {
    IntStream.rangeClosed(1, 50).forEach(i -> {
      Member member = Member.builder()
          .email("user" + i + "@naver.com")
          .password(passwordEncoder.encode("1111"))
          .nickname("nickname" + i)
          .role(MemberRole.MEMBER)
          .build();
      memberRepository.save(member);
    });
  }

  @Test
  public void testUpdate() {
    Member member = memberRepository.findById(2L).get();
    member.setNickname("flower");
    memberRepository.save(member);

    /*
     * Hibernate:
     * update
     * m_member
     * set
     * email=?,
     * nickname=?,
     * password=?,
     * role=?,
     * updatedate=?
     * where
     * mid=?
     */
  }

  @Transactional
  @Test
  public void testUpdate2() {
    memberRepository.updateNickName("pickmin", "user3@naver.com");
    /*
     * Hibernate:
     * update
     * m_member m1_0
     * set
     * m1_0.nickname=?
     * where
     * m1_0.email=?
     */
  }

  @Commit
  @Transactional
  @Test
  public void testDelete() {
    // 리뷰삭제(리뷰를 작성한 멤버를 이용해서 삭제)
    reviewRepository.deleteByMember(Member.builder().mid(49L).build());
    // 회원삭제
    memberRepository.deleteById(49L);
  }
}
