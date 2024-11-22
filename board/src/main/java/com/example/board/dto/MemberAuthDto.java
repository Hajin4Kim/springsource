package com.example.board.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Builder
@ToString
@Getter
@Setter
public class MemberAuthDto extends User {

  private MemberDto memberDto;

  // TODO: List 생성하는 다양한 방법
  // TODO: 1) List<String> list = new ArrayList<>();
  // list.add();

  // TODO: 2) private List<String> list = List.of("spring", "java", "sdk");

  // TODO: 3) List<String> list = Arrays.asList("spring", "java", "sdk");

  // TODO: Collection<? extends GrantedAuthority> : Generics //TODO: 담기 전에 어떤 것을
  // 담을 것인지 미리 선언
  public MemberAuthDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public MemberAuthDto(MemberDto memberDto) {

    // TODO: 해석하면 이런 의미이다.
    // List<SimpleGrantedAuthority> list => List.of(new
    // SimpleGrantedAuthority(memberDto.getRole().toString())));
    this(memberDto.getEmail(), memberDto.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_" + memberDto.getRole().toString())));
    this.memberDto = memberDto;
  }

}
