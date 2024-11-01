package com.example.project3.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "members")
@Entity
public class Team {

  @Id
  private String id;

  private String name;

  // TODO: left join 을 처음부터 하지 않음 => member 정보가 없음
  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL) // , fetch = FetchType.EAGER
  @Builder.Default
  private List<Member> members = new ArrayList<>();// TODO: Team 입장에서는 Member 가 여러 명 들어오니까 List

}