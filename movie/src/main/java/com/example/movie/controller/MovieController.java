package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

  @GetMapping("/list")
  public void getList() {
    log.info("전체 movie list 요청");

  }

}
