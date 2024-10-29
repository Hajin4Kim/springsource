package com.example.project2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

// @GeneratedValue(strategy = GenerationType.AUTO) : JPA 구현체가 자동으로 생성 전략 결정
// @GeneratedValue(strategy = GenerationType.IDENTITY) : 기본키 생성을 DB 에 위임하는 전략 (MySQL, PostgreSQL)

@SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", allocationSize = 1) // allocationSize = 1 씩 증가
@Entity
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
  private Long id;
}
