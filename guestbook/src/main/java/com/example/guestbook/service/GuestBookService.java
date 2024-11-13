package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;

public interface GuestBookService {

  // TODO: 등록
  Long register(GuestBookDto dto);

  // TODO: 하나 조회
  GuestBookDto read(Long gno);

  // TODO: 전체 조회
  PageResultDto<GuestBookDto, GuestBook> list(PageRequestDto requestDto);

  // TODO: 수정
  Long update(GuestBookDto dto);

  // TODO: 삭제
  void delete(Long gno);

  // TODO: dtoToEntity
  public default GuestBook dtoToEntity(GuestBookDto dto) {
    return GuestBook.builder()
        .gno(dto.getGno())
        .title(dto.getTitle())
        .content(dto.getContent())
        .writer(dto.getWriter())
        .build();
  }

  // TODO: entityToDto
  public default GuestBookDto entityToDto(GuestBook entity) {
    return GuestBookDto.builder()
        .gno(entity.getGno())
        .title(entity.getTitle())
        .content(entity.getContent())
        .writer(entity.getWriter())
        .regDate(entity.getRegDate())
        .updateDate(entity.getUpdateDate())
        .build();
  }
}
