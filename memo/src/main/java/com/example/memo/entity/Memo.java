package com.example.memo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: entity 패키지명 하단에 작성하는 클래스는 테이블 정의와 동일함
// memo 테이블 생성
// 메모번호(mno), 메모내용(memo_text)
// 기본값:  Long => number(19), String => varchar2(255), int => number(10)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// 시퀀스 만들기 @SequenceGenerator()
@SequenceGenerator(name = "memo_seq_gen", sequenceName = "memo_seq", allocationSize = 1)
@Entity // @Entity 와 @Id 어노테이션 붙이면 알아서 테이블 만들어줌
public class Memo extends BaseEntity {

  // 시퀀스 값 연동 @GeneratedValue()
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memo_seq_gen")
  @Id // @Entity 와 @Id 어노테이션 붙이면 알아서 테이블 만들어줌
  private Long mno;

  @Column(length = 200, nullable = false) // default 값이 nullable = true
  private String memoText;
}
