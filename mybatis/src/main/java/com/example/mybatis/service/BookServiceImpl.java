package com.example.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDto;
import com.example.mybatis.mapper.BookMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

  private final BookMapper bookMapper;

  @Override
  public Long create(BookDto dto) {
    return null;
  }

  @Override
  public BookDto getRow(Long id) {
    return null;
  }

  @Override
  public Long update(BookDto dto) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public List<CategoryDto> getCateList() {
    return null;
  }

  @Override
  public List<PublisherDto> getPubList() {
    return null;

  }

  @Override
  public List<BookDto> getList(PageRequestDto requestDto) {
    return bookMapper.listAll(requestDto);
  }

  @Override
  public int getTotalCnt(PageRequestDto requestDto) {
    return bookMapper.totalCnt(requestDto);
  }

}
