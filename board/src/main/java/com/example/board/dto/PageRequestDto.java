package com.example.board.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// http://localhost:8080/board/list?page=2&type=c&keyword=소년

@ToString
@Builder
@AllArgsConstructor
@Setter
@Getter
public class PageRequestDto {
  private int page;
  private int size;

  // 검색
  private String type;
  private String keyword;

  public PageRequestDto() { // TODO: Default Constructor
    this.page = 1;
    this.size = 10;

  }

  public Pageable getPageable(Sort sort) {
    // 0 : 1 page
    return PageRequest.of(page - 1, size, sort);
  }

}
