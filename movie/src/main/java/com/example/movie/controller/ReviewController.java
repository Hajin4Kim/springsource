package com.example.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.ReviewDto;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@Log4j2
@RestController
public class ReviewController {

  private final ReviewService reviewService;

  // ~~ /reviews/45/all
  @GetMapping("/{mno}/all")
  public List<ReviewDto> getList(@PathVariable Long mno) {
    log.info("리뷰 리스트 요청 {}", mno);

    List<ReviewDto> reviews = reviewService.getReviews(mno);

    return reviews;
  }

}
