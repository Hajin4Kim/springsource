package com.example.book.entity;

import lombok.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "category", "publisher" })
@Setter
@Getter
@Table(name = "booktbl")
@Entity
public class Book extends BaseEntity {

  // id, 제목, 저자, 출판일, 가격, 할인가격
  @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
  @Column(name = "book_id")
  @Id
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String writer;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  private Integer salePrice;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  private Publisher publisher;
}
