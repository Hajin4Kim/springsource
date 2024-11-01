package com.example.project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

public interface MemberRepository extends JpaRepository<Member, String> {

  // TODO: repository 생성
  // TODO: FROM 절에 올 떄 Entity 명으로 써야 함 -> 대소문자 구분 함
  @Query("SELECT m FROM Member m JOIN m.team t WHERE t.name = :name") // ON 은 안씀 이너조인 알아서 해줌
  public List<Member> finbyMemberEqualTeam(String name);

  List<Member> findByTeam(Team team);

}
