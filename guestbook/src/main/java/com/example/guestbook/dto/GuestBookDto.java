package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GuestBookDto {

  private Long gno;

  @NotBlank(message = "title은 필수 입력 요소입니다.")
  private String title;

  @NotBlank(message = "content는 필수 입력 요소입니다.")
  private String content;

  @NotBlank(message = "writer는 필수 입력 요소입니다.")
  private String writer;

  private LocalDateTime regDate;
  private LocalDateTime updateDate;

}
