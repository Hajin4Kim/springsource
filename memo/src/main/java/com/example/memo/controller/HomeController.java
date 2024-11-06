package com.example.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {
  @GetMapping("/") // http://localhost:8080/
  public String getHome() {
    return "index"; // http://localhost:8080/index
  }
}
