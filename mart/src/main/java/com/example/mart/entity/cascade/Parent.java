package com.example.mart.entity.cascade;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "children")
@Setter
@Getter
@Entity
public class Parent {

  @SequenceGenerator(name = "parent_seq_gen", sequenceName = "parent_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_seq_gen")
  @Id
  private Long id;

  @Column(nullable = false)
  private String name;

  // 양방향 통로 열기

  // TODO: 영속상태 / 영속성 전이
  // CascadeType.PERSIST : 영속 상태 전이
  // CascadeType.MERGE : 병합 상태 전이
  // CascadeType.REMOVE : 삭제 상태 전이
  // CascadeType.ALL : 전체 전이
  // CascadeType.REFRESH : REFRESH 상태 전이
  // CascadeType.DETACH : DETACH 상태 전이

  @Builder.Default
  // @OneToMany -> Default = LAZY
  // TODO: orphanRemoval = true : 부모 entity 에서 자식 entity 참조제거
  // 자식 entity 의 부모가 제거된 상태이고 고아가 되어버림
  // 고아가 된 entity 자동 삭제 true
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) // TODO: 부모 One -> Child Many
  private List<Child> children = new ArrayList<>();

}
