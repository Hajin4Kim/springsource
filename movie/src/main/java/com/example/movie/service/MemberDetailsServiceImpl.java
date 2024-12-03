package com.example.movie.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordDto;
import com.example.movie.entity.Member;
import com.example.movie.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MemberDetailsServiceImpl implements UserDetailsService, MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("service username : {}", username);

        // TODO: 로그인 요청
        Optional<Member> result = memberRepository.findByEmail(username); // username == email 로 넘어옴

        if (!result.isPresent()) { // username 이 없다면
            throw new UsernameNotFoundException("이메일 확인하세요");
        }

        // 이메일이 존재한다면 entity => dto 변경 (+ security 처리)
        Member member = result.get();

        MemberDto memberDto = MemberDto.builder()
                .mid(member.getMid())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .role(member.getRole())
                .build();

        return new AuthMemberDto(memberDto);
    }

    @Transactional
    @Override
    public void nickNameUpdate(MemberDto memberDto) {

        memberRepository.updateNickName(memberDto.getNickname(), memberDto.getEmail());
        // log.info("service nickNameUpdate :{}", memberDto.getNickname());
    }

    @Override
    public void passwordUpdate(PasswordDto passwordDto) throws Exception {
        // email 을 이용해 사용자 찾기
        // TODO: 이미 로그인 성공된 상황이라, 이 과정을 거칠 필요가 없음
        // Optional<Member> result =
        // memberRepository.findByEmail(passwordDto.getEmail());
        // if (!result.isPresent())throw new UsernameNotFoundException("이메일 확인하세요");

        Member member = memberRepository.findByEmail(passwordDto.getEmail()).get();

        // 현재 비번(DB에 저장된값)이 입력비번(사용자 화면단 입력값)과 일치하는지 검증 (암호화된 것과 암호화한 것 비교)
        if (!passwordEncoder.matches(passwordDto.getCurrentPassword(), member.getPassword())) { // TODO:
                                                                                                // member.getPassword()
                                                                                                // ==> 암호화된 상태인
            // false == 되돌려 보내기
            throw new Exception("현재 비밀번호를 확인");
        } else {
            // true == 새 비번으로 수정
            member.setPassword(passwordEncoder.encode(passwordDto.getNewPassword())); // TODO: 암호화해서 보내야함
            memberRepository.save(member);
        }

    }

}