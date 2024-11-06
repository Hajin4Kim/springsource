package com.example.memo.service;

import java.util.List;

import com.example.memo.entity.Memo;
import com.example.memo.dto.MemoDto;

public interface MemoService {

  // CRUD 메소드
  Long create(MemoDto dto); // Insert

  MemoDto read(Long id); // read one

  List<MemoDto> list(); // read all

  Long update(MemoDto dto); // update

  void delete(Long id); // delete

  // dto ==> entity
  public default Memo dtoToEntity(MemoDto dto) {
    return Memo.builder()
        .mno(dto.getMno())
        .memoText(dto.getMemoText())
        .build();
  }

  // entity ==> dto
  public default MemoDto entityToDto(Memo memo) {
    return MemoDto.builder()
        .mno(memo.getMno())
        .memoText(memo.getMemoText())
        .createdDateTime(memo.getCreatedDateTime())
        .lastModifiedDateTime(memo.getLastModifiedDateTime())
        .build();
  }
}
