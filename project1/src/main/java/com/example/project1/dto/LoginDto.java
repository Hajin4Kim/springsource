package com.example.project1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * validation (유효성 검증)
 * 
 * 사용자 입력값 검증 - 1. front단 : javascript 2. back단 : spring 지원
 * 
 * 주요 어노테이션
 * 1) @NotEmpty : NULL 체크, 문자열의 길이가 0인지 검사
 * 2) @NotBlank : NULL 체크, 문자열의 길이가 0인지 검사, 빈문자열("") 검사
 * 3) @Length(min=3,max=5) : 길이 검사
 * 4) @Email : 이메일 형식인지 검사
 * 5) @Max(8) / @Min(3) : 지정한 값보다 큰지 작은지 검사
 * 6) @Null : NULL 이어야 한다 => 검사
 * 7) @NotNull : NotNull 이어야 한다 => 검사
 * 8) @Size : 문자의 길이 검사
 * 9) @Pattern(regexp="정규식") : 정규식 패턴에 맞는지 검사
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data

public class LoginDto {
  @NotEmpty(message = "아이디는 필수 요소입니다.")
  private String userid;
  @NotBlank
  private String password;

}
