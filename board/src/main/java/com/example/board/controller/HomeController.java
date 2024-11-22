package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    log.info("home 요청");
    return "redirect:/board/list";
  }

}