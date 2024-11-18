package com.example.memo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.memo.dto.MemoDto;
import com.example.memo.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/rest")
@Log4j2
@RestController // TODO: 화면단은 어떤것이 들어와도 상관없음 (데이터 전달만 신경쓰기)
public class MemoRestController {

  private final MemoService memoService;

  @GetMapping("/list")
  public List<MemoDto> getList() {
    log.info("메모 전체 목록 요청");
    List<MemoDto> list = memoService.list();
    return list;
  }

  // TODO: /rest/1
  @GetMapping("/{mno}")
  public MemoDto getRead(@PathVariable Long mno) {
    log.info("메모 조회 {}", mno);

    MemoDto dto = memoService.read(mno);
    return dto;
  }

  // TODO: @RequestBody : json => 객체로 전환시켜줌
  @PostMapping("/create")
  public ResponseEntity<String> postCreate(@RequestBody MemoDto dto) {// TODO: @RequestBody 무조건
    log.info("메모작성 {}", dto);
    Long mno = memoService.create(dto);

    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  // TODO: rest 추가되는 method: PUT(or patch) / DELETE
  // @PutMapping
  @PutMapping("/{mno}")
  public ResponseEntity<String> postUpdate(@PathVariable Long mno, @RequestBody MemoDto dto) {
    log.info("수정 요청 {}", dto);
    dto.setMno(mno); // TODO: dto에 기존의 mno 번호를 입력
    memoService.update(dto);

    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @DeleteMapping("/{mno}")
  public ResponseEntity<String> getDelete(@PathVariable Long mno) {
    log.info("메모삭제 요청 {}", mno);
    memoService.delete(mno);

    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

  }
}
