package com.example.guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/guestbook")
@Controller
public class GuestBookController {

  private final GuestBookService service;

  @GetMapping("/list")
  public void getList(@ModelAttribute(name = "requestDto") PageRequestDto requestDto, Model model) {
    log.info("전체 목록 요청 {}", requestDto);

    PageResultDto<GuestBookDto, GuestBook> result = service.list(requestDto);
    model.addAttribute("result", result);
  }

  // TODO: 상세조회 페이지
  @GetMapping(value = { "/read", "/modify" }) // TODO: gno 값 넘겨서 /list 페이지에서 th:href 로 받아, controller 로 해당 gno 값에 대한 동작을
  public void getRow(@RequestParam Long gno, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
      Model model) {
    log.info("Read 상세 요청 {}", gno);
    GuestBookDto dto = service.read(gno);
    model.addAttribute("dto", dto);
  }

  @PostMapping("/modify")
  public String postModify(GuestBookDto dto, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
      RedirectAttributes rttr) {
    log.info("Update 수정 요청 {}", dto);
    log.info("requestDto {}", requestDto);

    Long gno = service.update(dto); // TODO: 수정된 번호 넘겨
    // TODO: 상세조회로 이동
    rttr.addAttribute("gno", gno); // TODO: addFlashAttribue 로 하면 redirect에서 사용 못함
    rttr.addAttribute("page", requestDto.getPage());
    rttr.addAttribute("size", requestDto.getSize());
    rttr.addAttribute("type", requestDto.getType());
    rttr.addAttribute("keyword", requestDto.getKeyword());
    return "redirect:read";
  }

  @PostMapping("/remove")
  public String getRemove(@RequestParam Long gno, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
      RedirectAttributes rttr) {
    log.info("guestbook 삭제 요청 {}", gno);
    log.info("requestDto {}", requestDto);

    service.delete(gno);

    // TODO: Pagination, 검색기능 추가
    rttr.addAttribute("page", requestDto.getPage());
    rttr.addAttribute("size", requestDto.getSize());
    rttr.addAttribute("type", requestDto.getType());
    rttr.addAttribute("keyword", requestDto.getKeyword());
    return "redirect:list";
  }

  @GetMapping("/register")
  public void getRegister(@ModelAttribute("dto") GuestBookDto dto, Model model) {
    log.info("guestbook 작성 폼 요청");

    // model.addAttribute(, model)
  }

  @PostMapping("/register")
  public String postRegister(@Valid @ModelAttribute(name = "dto") GuestBookDto dto, BindingResult result,
      RedirectAttributes rttr) {

    log.info("guestbook 등록 요청 {}", dto);

    if (result.hasErrors()) {
      return "/guestbook/register";
    }
    Long gno = service.register(dto);

    rttr.addFlashAttribute("msg", gno); // TODO: flash 는 주소줄엔 안따라가고 ${} 로 불러다 쓸 수 있음
    rttr.addAttribute("page", 1);
    rttr.addAttribute("size", 20);
    rttr.addAttribute("type", "");
    rttr.addAttribute("keyword", "");

    return "redirect:list";
  }

}
