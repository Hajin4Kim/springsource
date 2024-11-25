package com.example.movie.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewDto {

  private Long reviewNo;

  private int grade;

  private String text;

  // Movie 의 mno 담기
  private Long mno;

  // Member mid, nickname, email
  private Long mid;
  private String email;
  private String nickname;

  private LocalDateTime regDate; // 최초 생성 시간
  private LocalDateTime updateDate; // 최종 수정 시간

}
