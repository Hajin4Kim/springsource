package com.example.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

    @PreAuthorize("permitAll()") // TODO: 접근제한 설정
    @GetMapping("/")
    public String getHome() {
        return "home";
    }

}
