package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping("/list")
  public void getList(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {
    log.info("전체 movie list 요청");

    PageResultDto<MovieDto, Object[]> result = movieService.getList(pageRequestDto);
    model.addAttribute("result", result);
  }

  @GetMapping({ "/read", "/modify" })
  public void getRead(@RequestParam Long mno, Model model,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
    log.info("영화 상세 정보 요청 {}", mno);

    MovieDto movieDto = movieService.get(mno);
    model.addAttribute("movieDto", movieDto);
  }

  @PostMapping("/delete")
  public String postDelete(@RequestParam Long mno, @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      RedirectAttributes rttr) {
    log.info("영화 삭제 요청 {}", mno);

    movieService.delete(mno);

    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("size", pageRequestDto.getSize());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/movie/list";
  }

}