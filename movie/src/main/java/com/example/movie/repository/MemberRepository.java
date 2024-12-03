package com.example.movie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);

  // TODO: 닉네임 수정
  @Modifying // TODO: UPDATE 와 DELETE 는 무조건 @Modifying
  @Query("UPDATE Member m SET m.nickname=:nickname WHERE m.email=:email")
  void updateNickName(String nickname, String email);

}
