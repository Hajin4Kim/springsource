package com.example.project1.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.dto.LoginDto;
import com.example.project1.dto.SampleDto;

@Log4j2
@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    log.info("index 요청");

    model.addAttribute("name", "hong");

    LoginDto loginDto = new LoginDto("hong456", "honghong11");
    model.addAttribute("login", loginDto);

    // SampleDto sampleDto = new SampleDto();

    // sampleDto.setId(1L);
    // sampleDto.setFirst("Adam");
    // sampleDto.setLast("Savage");
    // sampleDto.setRegDateTime(LocalDateTime.now());

    // TODO: @ builder 패턴 :
    SampleDto sampleDto = SampleDto.builder()
        .id(1L)
        .first("Adam")
        .last("Savage")
        .regDateTime(LocalDateTime.now())
        .build();
    model.addAttribute("sampleDto", sampleDto);

    // 리스트 10번 반복
    List<SampleDto> list = new ArrayList<>();
    LongStream.rangeClosed(1, 10)
        .forEach(i -> {
          SampleDto dto = SampleDto.builder()
              .id(i)
              .first("first" + i)
              .last("last" + i)
              .regDateTime(LocalDateTime.now())
              .build();
          list.add(dto);
        });
    model.addAttribute("list", list);

    return "index";
  }
}
