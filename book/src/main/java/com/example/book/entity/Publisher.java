package com.example.book.entity;

import lombok.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
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
@Entity
public class Publisher extends BaseEntity {

  @SequenceGenerator(name = "book_publisher_seq_gen", sequenceName = "publisher_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_publisher_seq_gen")
  @Column(name = "publisher_id")
  @Id
  private Long id;

  @Column(nullable = false, name = "publisher_name")
  private String name;
}
