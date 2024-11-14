package com.example.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table
@Entity
public class Member extends BaseEntity {

  @Column(name = "email")
  @Id
  private String email;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;
}
