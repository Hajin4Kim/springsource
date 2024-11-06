package com.example.memo.dto;

import lombok.Builder; //TODO: lombok Builder 잘가져오기

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDto {
  private Long mno;

  @NotBlank(message = "내용 입력하시오") // TODO: 폼 유효성 검증
  private String memoText;

  private LocalDateTime createdDateTime; // 최초 생성 시간
  private LocalDateTime lastModifiedDateTime; // 최종 수정 시간
}
