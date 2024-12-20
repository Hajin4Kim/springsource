package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    log.info("home 요청");
    return "home";
  }

  @GetMapping("/auth")
  public Authentication getAuthentication() { // TODO: springframework.security.core
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();

    log.info("getAuthentication 요청");
    return authentication;
  }
}
