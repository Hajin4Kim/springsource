package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> { // TODO: <가져올 entity명, @Id 타입>
  // save(Entity) 메소드 : insert, update 실행됨
  // SELECT 구문 : findById(PK) - 하나 조회
  // SELECT 구문 : findAll() - 전체 조회
  // DELETE 구문 : deleteById(PK) - 하나 삭제
  // DELETE 구문 : delete(Entity) - 하나 삭제

}
