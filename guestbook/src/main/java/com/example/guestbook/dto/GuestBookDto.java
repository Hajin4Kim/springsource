package com.example.guestbook.dto;

import java.time.LocalDateTime;

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

  private String title;

  private String content;

  private String writer;

  private LocalDateTime regDate;
  private LocalDateTime updateDate;

}
