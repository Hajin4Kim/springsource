package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project1.dto.CalcDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
public class SampleController {

  // @RequestMapping(path="/basic", method = RequestMethod.GET) //
  // http://localhost:8080/basic
  // public void basic(){
  // log.info("basic 컨트롤러 동작"); // log = syso 랑 같은 쓸모
  // }

  // void : ("/basic") 가 templates 폴더 아래 경로로 자동 인식
  // ("/sample/ex2") => sample 폴더 밑에 ex2.html 이 된다.

  // String : redirect 시키는 경우, template 파일명을 임의대로 지정해야하는 경우

  // TODO: 입력값 가져오기
  // 1) HttpservletRequest 사용 가능 (입력값을 가져오는 용도로 잘 사용하지 않음)
  // 2) Parameter 선언 (변수명과 이름을 맞추는게 편함)
  // 3) DTO 사용(장점: POST 메소드 끝난 후 보여지는 페이지에서 DTO를 사용할 수 있음)
  // 사용규칙: (CalcDto calc) 에서 CalcDto => calcDto (앞자리 하나만 소문자로 바꾸어서 사용)

  // TODO: Controller 가 가지고 있는 값을 화면과 공유하기
  // 1) ~~DTO: (전제조건: Redirect로 움직이지 않은 경우)기본 공유 됨 (클래스명과 동일(맨첫글자만 소문자))
  // 2) 변수에 들어있는 값을 공유 : model.addAttribute("이름", 변수명)
  // model.addAttribute("이름", 객체명) //객체도 가능 변수만 가능한거 아님
  // 3) method(@ModelAttribute int bno) : bno 공유하고 싶다면
  // 4) method(@ModelAttribute("usDto") UserDto usDto) : UserDto 공유하고 싶은데 이름 다르게공유

  // TODO: Redirect 움직이는 경우
  // RedirectAttributes rttr 이용
  // 1) rttr.addAttribute("이름", 값) == 경로에 ? 다음에 따라가는 값의 형태 => ${param.이름}
  // 2) rttr.addFlashAttribute("이름", 값) == session 이용하기 때문에 따라가는 것은 안보임 => ${이름}

  // Model 과 RedirectAttributes 차이점 : 움직이는 방식 / 객체를 담을 수 있느냐? 없느냐? => MODEL 이 DTO
  // 가능

  @GetMapping("/basic2")
  public String basic2(RedirectAttributes rttr) {
    log.info("basic2 컨트롤러 동작");
    // sendRedirect() == redirect:경로
    // 스프링에서 redirect 움직이는 방식

    // TODO: http://localhost:8080/ex1?age=15
    rttr.addAttribute("age", 15); // redirect 시, 주소의 parameter 로 딸려 보내기
    rttr.addAttribute("name", "hong"); // redirect 시, 주소의 parameter 로 딸려 보내기

    return "redirect:/ex1";
    // 이거랑 같은거 return "redirect:/ex1?age=15&name=hong";
  }

  @GetMapping("/basic")
  public String basic(RedirectAttributes rttr) {
    log.info("basic 컨트롤러 동작");

    // http://localhost:8080/ex1;jsessionid=64C1177F9B75F136862146C8DA990A4C
    // rttr.addFlashAttribute() == session 을 사용하는 것과 동일하나, 일시적 보관
    // TODO: addFlashAttribute: ?addr=seoul 없음
    rttr.addFlashAttribute("addr", "hong");
    return "redirect:/ex1";
  }

  @GetMapping("/ex1")
  public void getEx1() {
    log.info("ex1 컨트롤러 동작");
  }

  @GetMapping("/sample/ex2")
  public void getEx2() {
    // void 아니면 String 만 만듬 (get)
    log.info("ex2 컨트롤러 동작");
  }

  @GetMapping("/ex3")
  public String getEx3() {
    log.info("ex3 컨트롤러 동작");
    return "test";
  }

  @GetMapping("/ex4")
  public String getEx4() {
    log.info("ex4 컨트롤러 동작");
    return "/sample/ex2";
  }

  // calc1 보여주기
  // http://localhost:8080/sample/calc1
  @GetMapping("/sample/calc1")
  public void getCalc1() {
    // void 아니면 String 만 만듬 (get)
    log.info("calc1 컨트롤러 동작");
  }

  // // 2번방법
  // @PostMapping("/sample/calc1")
  // public void postCalc1(int num1, int num2) {
  // log.info("calc 입력값 가져오기");
  // log.info("{} + {} = {}", num1, num2, (num1 + num2));
  // }

  // 3번방법
  @PostMapping("/sample/calc1")
  public void postCalc1(CalcDto calc, Model model) { // import org.springframework.ui.Model;
    log.info("calc 입력값 가져오기");
    log.info("{} + {} = {}", calc.getNum1(), calc.getNum2(), (calc.getNum1() + calc.getNum2()));

    int result = calc.getNum1() + calc.getNum2();

    // result 값을 화면에 보여주기 (setAttribute 개념)
    // TODO: Model 사용
    model.addAttribute("result", result);

  }

  @GetMapping("/sample/calc2")
  public void getCalc2() {
    log.info("calc2 폼 요청");
  }

  // // POST 2번째 방법
  // @PostMapping("/sample/calc2")
  // public void postCalc2(int num1 ,int num2, String op) {
  // log.info("calc 입력값 가져오기");
  // log.info("{} {} {}", num1, op, num2);

  // int result = 0;
  // switch (op) {
  // case "+":
  // result = num1 + num2;
  // break;
  // case "-":
  // result = num1 - num2;
  // break;
  // case "*":
  // result = num1 * num2;
  // break;
  // case "/":
  // result = num1 / num2;
  // break;
  // case "%":
  // result = num1 % num2;
  // break;
  // default:
  // break;
  // }
  // log.info("{} {} {} = {}", num1, op, num2, result);
  // }

  // POST 3번째 방법
  @PostMapping("/sample/calc2")
  public void postCalc2(CalcDto calc, Model model) { // TODO: 보낼 값 셋팅 Model
    log.info("calc 입력값 가져오기");
    int result = 0;
    switch (calc.getOp()) {
      case "+":
        result = (calc.getNum1() + calc.getNum2());
        break;
      case "-":
        result = (calc.getNum1() - calc.getNum2());
        break;
      case "*":
        result = (calc.getNum1() * calc.getNum2());
        break;
      case "/":
        result = (calc.getNum1() / calc.getNum2());
        break;
      case "%":
        result = (calc.getNum1() % calc.getNum2());
        break;
      default:
        break;
    }
    log.info("{} {} {} = {}", calc.getNum1(), calc.getOp(), calc.getNum2(), result);
    model.addAttribute("result", result); // TODO: 보낼 값 셋팅 Model

  }

}
