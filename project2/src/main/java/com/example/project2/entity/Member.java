package com.example.project2.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.project2.entity.constant.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 테이블 작성
// id, username, age 
// 회원가입일, 수정일이 필요, 
// 회원 - 관리자, 회원으로 구분됨

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// @SequenceGenerator(name = "memo_seq_gen", sequenceName = "memo_seq",
// allocationSize = 1)
@Entity(name = "membertbl")
public class Member {

  @Id
  private String id;

  @Column(name = "name") // sql 내에선 name 으로 만들어지게끔
  private String userName;

  private int age;

  // 회원가입일, 수정일
  private RoleType roleType;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}
