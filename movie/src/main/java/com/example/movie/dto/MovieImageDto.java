package com.example.movie.dto;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

  public String getThumbImageURL() {
    // TODO: 썸네일 이미지 경로 리턴을 위한 메소드
    String fullPath = "";
    try {
      fullPath = URLEncoder.encode(path + File.separator + "s_" + uuid + "_" + imgName, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return fullPath;
  }

  public String getImageURL() {
    // TODO: 원본 이미지 경로 리턴을 위한 메소드
    String fullPath = "";
    try {
      fullPath = URLEncoder.encode(path + File.separator + uuid + "_" + imgName, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return fullPath;
  }
}
