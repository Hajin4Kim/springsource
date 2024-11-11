package com.example.project2.repository.jpql;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.project2.entity.Member;
import com.example.project2.entity.jpql.JpqlMember;
import com.example.project2.entity.jpql.Team;

public interface JpqlMemberRepository extends JpaRepository<JpqlMember, Long>, QuerydslPredicateExecutor<JpqlMember> {

  // TODO: findAll () => @Query 로 직접 구현
  @Query("SELECT m FROM JpqlMember m")
  List<JpqlMember> findMembers(Sort sort);

  // TODO: SELECT 다음에 오는 구문이 2개 이상인 경우 Object[]배열로 무조건 / @Query 에서 개별로 갖고 나오기
  @Query("SELECT m.name, m.age FROM JpqlMember m")
  List<Object[]> findMembers2();

  // 쿼리메소드
  // 특정 이름을 가진 회원 조회
  @Query("SELECT m FROM JpqlMember m WHERE m.name=?1")
  List<Member> findByName(String name);

  // 특정 나이보다 많은 회원 조회 (팀정보를 받아서 특정 팀의 회원을 추출 (JPK))
  @Query("SELECT m FROM JpqlMember m WHERE m.age>?1")

  List<Member> findByAgeGreaterThan(int age);

  // 특정 팀 회원 조회
  @Query("SELECT m FROM JpqlMember m WHERE m.team=?1")
  List<JpqlMember> findByTeam(Team team);

  // 나이 합계, 나이평균, 연장자, 최연소, 인원수 정보 추출
  @Query("SELECT COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) FROM JpqlMember m")
  List<Object[]> aggregate();

  // TODO: 내부조인인 경우 ON절 생략
  @Query("SELECT m FROM JpqlMember m JOIN m.team t WHERE t.name = ?1")
  List<JpqlMember> findByTeam2(String teamName);

  @Query("SELECT m,t FROM JpqlMember m JOIN m.team t WHERE t.name = ?1")
  List<Object[]> findByTeam3(String teamName);

  // TODO: 3번방법 : nativeQuery = true
  @Query(value = "SELECT * FROM JPQL_MEMBER jm JOIN JPQL_TEAM jt ON jm.TEAM_ID = jt.ID WHERE jt.NAME = ?1", nativeQuery = true)
  List<Object[]> findByTeam4(String teamName);

  // 외부조인
  @Query("SELECT m,t FROM JpqlMember m LEFT JOIN m.team t ON t.name = ?1")
  List<Object[]> findByTeam5(String teamName);

}
