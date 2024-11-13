package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/guestbook")
@Controller
public class GuestBookController {

  private final GuestBookService guestBookService;

  // @GetMapping("/list")
  // public void list() {
  // log.info("list 요청");
  // }

  @GetMapping("/list")
  public void getList(@ModelAttribute(name = "requestDto") PageRequestDto requestDto, Model model) {
    log.info("전체 목록 요청 {}", requestDto);

    PageResultDto<GuestBookDto, GuestBook> result = guestBookService.list(requestDto);
    model.addAttribute("result", result);
  }
}
