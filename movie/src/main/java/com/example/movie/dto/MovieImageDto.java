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
public class MovieImageDto {

  private Long inum;

  private String uuid;

  private String imgName;

  private String path;

  private LocalDateTime regDate; // 최초 생성 시간
  private LocalDateTime updateDate; // 최종 수정 시간
}
