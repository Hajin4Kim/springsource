package com.example.movie.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieDto {

  private Long mno;
  private String title;

  // 영화에 소속된 이미지 가져오기
  @Builder.Default
  private List<MovieImageDto> movieImageDtos = new ArrayList<>();

  // 영화 평균 평점
  private Double reviewAvg;

  // 영화 평점 개수
  private Long reviewCnt;

  private LocalDateTime regDate; // 최초 생성 시간
  private LocalDateTime updateDate; // 최종 수정 시간

}
