package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping("/list")
  public void getList(PageRequestDto pageRequestDto, Model model) {
    log.info("전체 movie list 요청");

    PageResultDto<MovieDto, Object[]> result = movieService.getList(pageRequestDto);
    model.addAttribute("result", result);
  }

}
