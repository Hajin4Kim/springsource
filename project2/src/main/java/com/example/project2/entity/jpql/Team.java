package com.example.project2.entity.jpql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "jpql_team")
@Entity
public class Team {

  @SequenceGenerator(name = "jpql_team_seq_gen", sequenceName = "jpql_team_seq", allocationSize = 1) // allocationSize =
                                                                                                     // 1 씩 증가
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpql_team_seq_gen")
  @Id
  private Long id;

  private String name;
}
