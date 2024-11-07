package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PublisherDto;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/book")
@Controller
public class BookController {

  private final BookService bookService;

  @GetMapping("/list")
  public void getList(Model model) {
    log.info("도서 전체 목록 요청");
    List<BookDto> list = bookService.getList();
    model.addAttribute("list", list);
  }

  // TODO: 상세조회 페이지
  @GetMapping(value = { "/read", "/modify" }) // TODO: id 값 넘겨서 /list 페이지에서 th:href 로 받아, controller 로 해당 id 값에 대한 동작을
                                              // 하게끔
  public void getRead(@RequestParam Long id, Model model) {
    log.info("도서 상세 요청 {}", id);
    BookDto dto = bookService.getRow(id);
    model.addAttribute("dto", dto);
  }

  @PostMapping("/modify")
  public String postModify(BookDto dto, RedirectAttributes rttr) {
    log.info("도서 수정 요청 {}", dto);
    Long id = bookService.update(dto); // TODO: 수정된 번호 넘겨
    rttr.addAttribute("id", id); // TODO: addFlashAttribue 로 하면 없어지므로 조심
    return "redirect:read";
  }

  @PostMapping("/remove")
  public String getRemove(@RequestParam Long id) {
    log.info("삭제 요청 {}", id);
    bookService.delete(id);
    // rttr.addAttribute("id", id);
    return "redirect:list";
  }

  @GetMapping("/create")
  public void getCreate(@ModelAttribute("dto") BookDto dto, Model model) {
    log.info("도서 입력 폼 요청");

    List<CategoryDto> categories = bookService.getCateList();
    List<PublisherDto> publisherDtos = bookService.getPubList();

    model.addAttribute("cDtos", categories);
    model.addAttribute("pDtos", publisherDtos);

  }

  @PostMapping("/create")
  public String postCreate(@Valid @ModelAttribute("dto") BookDto dto, BindingResult result, RedirectAttributes rttr,
      Model model) {
    log.info("도서 추가 요청{}", dto);

    List<CategoryDto> categories = bookService.getCateList();
    List<PublisherDto> publisherDtos = bookService.getPubList();

    model.addAttribute("cDtos", categories);
    model.addAttribute("pDtos", publisherDtos);

    if (result.hasErrors()) {
      return "/book/create"; // TODO: forward로 돌려보내는 구조
    }
    // 서비스 insert 호출
    Long id = bookService.create(dto);
    rttr.addFlashAttribute("msg", id + "번 도서가 등록되었습니다");
    return "redirect:list";
  }
}
