package com.example.todo.service;

import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class TodoServiceImpl implements TodoService {

  @Autowired // TODO: ==> 스프링 컨테이너가 관리하는 Bean 중에서 TodoRepository 주입해줘
  // TodoRepository todoRepository = new TodoRepository() 와 같은 뜻
  private final TodoRepository todoRepository;

  @Override
  public List<TodoDto> getList(Boolean completed) {

    // List<Todo> result = todoRepository.findAll();
    // // TODO: Entity 를 하나씩 DTO 로 바꿔 수집해서 새로운 list 로 return
    // // List<TodoDto> list = new ArrayList<>();
    // // result.forEach(entity -> {
    // // list.add(entityToDto(entity));
    // // });

    // //TODO: 전체 todos
    // List<TodoDto> list = result
    // .stream()
    // .map(todo -> entityToDto(todo))
    // .collect(Collectors.toList());

    // TODO: 미완료 todos / 완료 todos
    List<Todo> result = todoRepository.findByCompleted(completed); // Boolean completed
    List<TodoDto> list = result.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());
    return list;
  }

  @Override
  public TodoDto getRow(Long id) {
    Todo todo = todoRepository.findById(id).get();
    return entityToDto(todo);
  }

  @Override
  public TodoDto create(TodoDto dto) {
    /*
     * TODO: insert 는 client 쪽에서 값을 받아오기 때문에
     * dto => entity 후 다시 entityToDto 진행 해야한다
     */
    Todo todo = dtoToEntity(dto);
    return entityToDto(todoRepository.save(todo));
  }

  @Override
  public List<TodoDto> getCompletedList() {
    return null;
  }

  @Override
  public Long updateCompleted(TodoDto dto) {
    Todo todo = todoRepository.findById(dto.getId()).get();
    todo.setCompleted(dto.getCompleted());
    Todo updateTodo = todoRepository.save(todo);
    return updateTodo.getId();
  }

  @Override
  public void deleteRow(Long id) {
    todoRepository.deleteById(id);
  }

}
