package com.example.project3.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

@SpringBootTest
public class TeamMemberRepositoryTest {

  @Autowired
  private TeamRepository teamRepository;
  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void createTest() {
    Team team = Team.builder()
        .id("team1")
        .name("팀1")
        .build();
    teamRepository.save(team);

    team = Team.builder()
        .id("team2")
        .name("팀2")
        .build();
    teamRepository.save(team);
  }

  @Test
  public void createMemberTest() {

    // //TODO: 방법 1_ team1 객체로 생성 해서 찾아와서 값을 집어넣는다 (foreign key)
    Team team1 = teamRepository.findById("team1").get();

    // TODO: 방법 2_ builder 로
    Team team2 = Team.builder().id("team2").build();

    IntStream.rangeClosed(1, 5).forEach(i -> {
      Member member = Member.builder()
          .id("user" + i)
          .userName("성춘향" + i)
          .team(team2)
          .build();
      memberRepository.save(member);
    });

    IntStream.rangeClosed(6, 10).forEach(i -> {
      Member member = Member.builder()
          .id("user" + i)
          .userName("성춘향" + i)
          .team(team1)
          .build();
      memberRepository.save(member);
    });

  }

  @Test
  public void selectTest() {
    // 회원 찾기
    Member member = memberRepository.findById("user1").get();
    System.out.println(member);

    // 팀 정보 전체 찾기
    System.out.println(member.getTeam());
    // 팀명 찾기
    System.out.println(member.getTeam().getName());
  }

  @Test
  public void memberEqualTeamTest() {
    memberRepository.finbyMemberEqualTeam("팀1").forEach(m -> System.out.println(m));
  }

  @Test
  public void updateTest() {
    // user6의 팀 변경하기 (team 2로)
    Member member = memberRepository.findById("user6").get();

    Team team2 = teamRepository.findById("team2").get();
    member.setTeam(team2);
    memberRepository.save(member);
  }

  @Test
  public void deleteTest() {
    // // team1 제거
    // teamRepository.deleteById("team1");
    // // 무결성 제약조건 에러 => 자식레코드 부터 지워야함 (외래키 제약조건에서)
    // // 자식의 팀 변경 VS 같이 삭제하던지

    Team team = Team.builder().id("team1").build();
    List<Member> members = memberRepository.findByTeam(team);
    // members.forEach(member -> System.out.println(member));

    Team team2 = teamRepository.findById("team2").get();

    // 다 team2 로 바꾸어버리고
    members.forEach(member -> {
      member.setTeam(team2);
      memberRepository.save(member);
    });

    // team1 열 제거
    teamRepository.deleteById("team1");

  }

  // member 삽입하면서 team 삽입이 가능한가?
  // SQL 에서 1) 부모삽입 2) 자식삽입
  // JPA 에서는 객체 형태로 다루니까 가능하지않을까?
  @Test
  public void memberAndTeamInsertTest() {

    // ERROR: Unable to find com.example.project3.entity.Team with id team3 at
    // org.hibernate.jpa.boot.
    // TODO: @~~~~관계(cascade = CascadeType.ALL) :설정이 없는 경우 위와 같은 에러남
    Team team = Team.builder().id("team3").name("팀3").build();
    Member member = Member.builder().id("user11").userName("김말숙").team(team).build();
    memberRepository.save(member);
  }

  @Test
  public void memberAndTeamUpdateTest() {

    Team team = teamRepository.findById("team3").get();
    team.setName("victory");
    // memberRepository.save(team);

    Member member = Member.builder().id("user11").userName("김말숙").team(team).build();
    memberRepository.save(member);
  }

  // TODO: 양방향 관계설정
  @Transactional // import SpringFramework...
  @Test
  public void selectMemberTest() {
    // 팀 찾기
    Team team2 = teamRepository.findById("team2").get();

    // TODO: Team.java 쪽에서 left join 을 처음부터 하지 않음 => member 정보가 없음
    // => 당연히 LazyInitializationException 남
    team2.getMembers().forEach(member -> {
      // member 정보 출력
      System.out.println(member);

      // 팀에 속한 멤버 이름 출력
      System.out.println(member.getUserName());
    });
  }

  // TODO: 양방향 insert
  @Test
  public void TeamAndmemberInsertTest() {

    // id team3
    Team team = Team.builder().id("team3").name("팀3").build();
    // teamRepository.save(team);
    Member member = Member.builder().id("user12").userName("수선화").team(team).build();
    team.getMembers().add(member);
    teamRepository.save(team);

    // //TODO: 원래 방식대로라면... (단방향)
    // 1. Team team4 = Team.builder().id("team4").name("팀4").build();
    // 2. teamRepository.save(team4);
    // 3. Member member4 =
    // Member.builder().id("user14").userName("수선화").team(team).build();
    // 4. memberRepository.save(member4);

  }
}
