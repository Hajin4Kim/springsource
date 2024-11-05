package com.example.mart.entity.cascade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "parent")
@Setter
@Getter
@Entity
public class Child {

  @SequenceGenerator(name = "child_seq_gen", sequenceName = "child_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_seq_gen")
  @Id
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne // TODO: Child 를 통해서 Parent 에 접근
  private Parent parent;
}
