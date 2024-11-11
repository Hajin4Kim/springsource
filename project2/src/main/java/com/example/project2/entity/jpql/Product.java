package com.example.project2.entity.jpql;

import jakarta.persistence.Column;
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
@Table(name = "jpql_product")
@Entity
public class Product {

  @SequenceGenerator(name = "jpql_product_seq_gen", sequenceName = "jpql_product_seq", allocationSize = 1) // allocationSize
                                                                                                           // =
  // 1 씩 증가
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpql_product_seq_gen")
  @Column(name = "product_id")
  @Id
  private Long id;

  private int price;

  private int stockAmount;

  private String name;

}
