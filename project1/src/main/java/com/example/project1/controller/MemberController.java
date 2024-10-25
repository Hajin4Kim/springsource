package com.example.project1.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.dto.LoginDto;
import com.example.project1.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/member")

public class MemberController {

  @GetMapping("/login")
  public void getLogin() {
    // form 양식 보여주는 용도 (페이지 요청)
    log.info("login GET 페이지 요청");
  }

  // // 첫번째 방법 : 잘 안씀
  // @PostMapping("/login")
  // public void postLogin(HttpServletRequest request ) {
  // // 이런 메소드 하나 하나가 ACtion 임
  // // 사용자의 입력값 (form input) 가져오는 용도
  // log.info("login POST 요청 - 사용자 입력값 요청");

  // String userid = request.getParameter("userid");
  // String password = request.getParameter("password");

  // log.info("userid : {} , password {}", userid, password);
  // }

  // // 두번째 방법 : name 과 변수명 맞추기
  // @PostMapping("/login")
  // public void postLogin(String userid, String password) {
  // log.info("login POST 요청 - 사용자 입력값 요청");
  // log.info("userid : {} , password {}", userid, password);
  // }

  // // 세번째 방법 : DTO 만들기
  // @PostMapping("/login")
  // public void postLogin(LoginDto loginDto) {
  // log.info("login POST 요청 - 사용자 입력값 요청");
  // // log.info("userid : {} , password {}", loginDto.getUserid(),
  // loginDto.getPassword());
  // }

  // TODO: 컨트롤러 클래스에 사용가능한 어노테이션
  @PostMapping("/login")
  public String postLogin(@ModelAttribute("login") LoginDto loginDto) {
    log.info("login POST 요청 - 사용자 입력값 요청");
    log.info("userid : {} , password {}", loginDto.getUserid(), loginDto.getPassword());
    return "index";
  }

  // // TODO: 컨트롤러에서 메소드 생성 방법
  // // http://localhost:8080/path1 + get
  // @GetMapping("/path1")
  // public String method1(){
  // return "login"; // login.html
  // }

  // // http://localhost:8080/path2 + post
  // @PostMapping("/path2") // path2.html
  // public void method2(){
  // TODO: 1. 입력값 가져오기 (form 에서)
  // TODO: 2. Service 호출 후 결과 받기
  // TODO: 3. model.addAttribute()
  // TODO: 4. 페이지 이동 (return 그냥 OR Redirect)
  // }

  // @GetMapping("/path1")
  // public String method3(){
  // return "redirect:/login"; // http://localhost:8080/login
  // }

  // TODO: Caused by: Ambiguous mapping ERROR: 동일한 경로로 중복되어 있음
  // Caused by: java.lang.IllegalStateException: Ambiguous mapping. Cannot map
  // 'memberController' method
  // com.example.project1.controller.MemberController#method1()
  // to {GET [/member/path1]}: There is already 'memberController' bean method
  // com.example.project1.controller.MemberController#method3() mapped.

  @GetMapping("/register") // TODO: /register.html
  public void getRegister() {
    log.info("register 요청");
  }

  // post // return => 로그인 페이지
  // TODO: url 주소창, a 링크로 들어가는 페이지는 모두 GET 방식; POST는 return 해줘야 갈 수 있는 페이지
  @PostMapping("/register")
  public String postRegister(@ModelAttribute("register") MemberDto memberDto) {
    log.info("register POST 요청 - 사용자 입력값 요청");
    log.info("userid : {} , password : {}, name : {}", memberDto.getUserid(), memberDto.getPassword(),
        memberDto.getName());
    return "redirect:/member/login"; // TODO: redirect:/member/login => (redirect:)경로 not html
    // http://localhost:8080/login
  }

}
