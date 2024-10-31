package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.dto.TodoDto;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo")

@Controller
public class TodoController {

  private final TodoService todoService; // TODO: final 짝궁은 @RequiredArgsConstructor

  @GetMapping("/list")
  public void getList(Boolean completed, Model model) { // TODO: todos 완료여부 completed
    log.info("todo 목록 페이지 요청");

    List<TodoDto> list = todoService.getList(completed);
    model.addAttribute("list", list);
  }

  @GetMapping("/read") // /todo/read?id=1
  public void getRead(@RequestParam Long id, Model model) { // TODO: @RequestParam
    log.info("todo 상세 요청 id={}", id);

    TodoDto dto = todoService.getRow(id);
    model.addAttribute("dto", dto);
  }

  @GetMapping("/create")
  public void getCreate() {
    log.info("todo 입력 폼 띄우기 요청");
  }

  @PostMapping("/create")
  public String postCreate(TodoDto dto) {
    log.info("todo 입력 요청 {}", dto);
    todoService.create(dto);
    return "redirect:/todo/list";
  }

  @PostMapping("/update")
  public String postUpdate(TodoDto dto, RedirectAttributes rttr) {
    log.info("todo 수정 요청 {}", dto);
    todoService.updateCompleted(dto);
    rttr.addAttribute("id", dto.getId()); // TODO: 상세페이지로 들어갈 수 있게 id 값 딸려보내기
    return "redirect:/todo/read";
  }

  @PostMapping("/delete")
  public String postDelete(@RequestParam Long id) {
    log.info("todo 삭제 요청 {}", id);
    todoService.deleteRow(id);
    return "redirect:/todo/list";
  }

}
