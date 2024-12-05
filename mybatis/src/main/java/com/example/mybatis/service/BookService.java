package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDto;

public interface BookService {

  // TODO: CRUD
  Long create(BookDto dto); // TODO: Create

  BookDto getRow(Long id); // TODO: Read one

  List<BookDto> getList(PageRequestDto requestDto);// TODO: Read ALl //TODO: Page나누기 개념 추가

  int getTotalCnt(PageRequestDto requestDto);

  Long update(BookDto dto); // TODO: update

  void delete(Long id); // TODO: delete

  List<CategoryDto> getCateList();// TODO: selectbox 카테고리명

  List<PublisherDto> getPubList();// TODO: selectbox 출판사명

}
