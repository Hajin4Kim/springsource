package com.example.todo.service;

import java.util.List;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;

public interface TodoService {
  List<TodoDto> getList(Boolean completed);

  TodoDto getRow(Long id);

  TodoDto create(TodoDto dto);

  List<TodoDto> getCompletedList();

  Long updateCompleted(TodoDto dto);

  void deleteRow(Long id);

  // TODO: dto ==> entity 작업
  public default Todo dtoToEntity(TodoDto dto) {
    return Todo.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .completed(dto.getCompleted())
        .important(dto.getImportant())
        .build();
  }

  // TODO: entity ==> dto 작업
  public default TodoDto entityToDto(Todo entity) {
    return TodoDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .completed(entity.getCompleted())
        .important(entity.getImportant())
        .build();
  }
}
