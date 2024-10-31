package com.example.todo.repository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

import java.util.stream.IntStream;

@SpringBootTest
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Test
  public void insertTest() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Todo todo = Todo.builder()
          // TODO: id 는 자동으로 할당됨 (1 ~ 10) => SEQUENCE 이기 때문
          .title("할 일 " + i)
          .build();
      // // 새로 삽입된 return
      // System.out.println(todoRepository.save(todo));
      todoRepository.save(todo);
    });
  }

  @Test
  public void selectAllTest() {
    todoRepository.findAll().forEach(todo -> System.out.println(todo));
  }

  @Test
  public void selectOneTest() {
    System.out.println(todoRepository.findById(5L).get());
  }

  @Test
  public void updateTest() {
    // completed, important 수정
    Todo todo = todoRepository.findById(7L).get();
    todo.setCompleted(true);
    todo.setImportant(true);
    todoRepository.save(todo);
  }

  @Test
  public void deleteTest() {
    todoRepository.deleteById(10L);
  }

  // TODO: TodoRepository interface에 생성한 findBy 테스트
  @Test
  public void completedTest() {
    // 완료 todos
    todoRepository.findByCompleted(true).forEach(todo -> System.out.println(todo));
    // 미완료 todos
    todoRepository.findByCompleted(false).forEach(todo -> System.out.println(todo));

  }
}
