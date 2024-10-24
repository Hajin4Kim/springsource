package com.example.project1.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {

  // @RequestMapping(path="/basic", method = RequestMethod.GET) // http://localhost:8080/basic
  // public void basic(){
  //   log.info("basic 컨트롤러 동작"); // log = syso 랑 같은 쓸모
  // }

  @GetMapping("/basic")
  public void basic() {
    log.info("basic 컨트롤러 동작");
  }
  
}
