package com.example.mart.entity.item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.example.mart.entity.constant.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
@ToString(exclude = { "member", "orderItemsList" })
@Setter
@Getter
@SequenceGenerator(name = "mart_order_seq_gen", sequenceName = "mart_order_seq", allocationSize = 1)
@Table(name = "mart_orders")
@Entity
public class Order {

  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mart_order_seq_gen")
  @Column(name = "order_id")
  @Id
  private Long id;

  @CreatedDate
  private LocalDateTime orderDate;

  @Enumerated(EnumType.STRING) // DB 테이블에 0, 1 대신 입력해둔 String 값으로 나오도록 설정
  private OrderStatus status; // enum

  @ManyToOne // Order 가 Many 인 쪽
  private Member member; // 관계설정

  // OrderItem ==> Order로 접근하는 관계는 OrderItem*(외래키가 있는 쪽) 쪽에 설정
  // 왜? -> 외래키 있는 쪽에 관계 설정

  // Order ==> OrderItem 접근하기
  // order.getOrderItem()
  // , fetch = FetchType.EAGER
  // TODO: Order 를 기준으로 OrderItem과 양방향 통로 열기
  @Builder.Default
  @OneToMany(mappedBy = "order")
  // TODO: Order 에서 OrderITem 은 여러 개 가져올 수 있으므로 List<>
  private List<OrderItem> orderItemsList = new ArrayList<>();
}
