package com.example.project2.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.project2.entity.Member;
import com.example.project2.entity.jpql.Address;
import com.example.project2.entity.jpql.JpqlMember;
import com.example.project2.entity.jpql.Order;
import com.example.project2.entity.jpql.Product;
import com.example.project2.entity.jpql.QJpqlMember;
import com.example.project2.entity.jpql.QProduct;
import com.example.project2.entity.jpql.Team;
import com.example.project2.repository.jpql.JpqlMemberRepository;
import com.example.project2.repository.jpql.OrderRepository;
import com.example.project2.repository.jpql.ProductRepository;
import com.example.project2.repository.jpql.TeamRepository;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class OrderRepositoryTest {

  @Autowired
  private JpqlMemberRepository jpqlMemberRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void insertTest() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Team team = Team.builder().name("team" + i).build();
      teamRepository.save(team);

      JpqlMember member = JpqlMember.builder().name("user" + i).age(i).team(team).build();
      jpqlMemberRepository.save(member);

      Product product = Product.builder().name("제품" + i).price(i * 1000).stockAmount(i * 5).build();
      productRepository.save(product);
    });
  }

  @Test
  public void insertOrderTest() {

    Address address = new Address();
    address.setCity("서울시");
    address.setStreet("152-1");
    address.setZipcode("11017");

    // 2번 member 가 3번 제품을 주문한다
    LongStream.rangeClosed(1, 3).forEach(i -> {
      Order order = Order.builder()
          .address(address)
          .member(JpqlMember.builder().id(2L).build())
          .product(Product.builder().id(i).build())
          .build();
      orderRepository.save(order);
    });
  }

  @Test
  public void testFindAll() {
    // jpqlMemberRepository.findMembers().forEach(m -> System.out.println(m));

    // TODO: 전체조회 (오름차순)
    // [JpqlMember(id=1, age=1, name=user1), JpqlMember(id=2, age=2,
    // name=user2),...]
    // System.out.println(jpqlMemberRepository.findMembers());

    // TODO: 정렬을 다른 컬럼(오름차순) ==> (Sort.by("컬럼명"))
    // System.out.println(jpqlMemberRepository.findMembers(Sort.by("age")));
    // System.out.println(jpqlMemberRepository.findMembers(Sort.by(Sort.Direction.DESC,
    // "age")));
    List<Object[]> list = jpqlMemberRepository.findMembers2();
    for (Object[] objects : list) {
      System.out.println(Arrays.toString(objects));
      System.out.printf("name = %s, age = %d\n", objects[0], objects[1]);
    }
  }

  @Test
  public void testAddress() {
    System.out.println((orderRepository.findByAddress()));
  }

  @Test
  public void testOrders() {
    System.out.println((orderRepository.findByOrders()));

    // for (Object[] objects : result) {
    // // System.out.println(Arrays.toString(objects));

    // JpqlMember member = (JpqlMember) objects[0];
    // Product product = (Product) objects[1];
    // int orderAmount = (Integer) objects[2];

    // System.out.println(member);
    // System.out.println(product);
    // System.out.println(orderAmount);
    // }

  }

  @Test
  public void testQueryMethod() {
    // System.out.println(jpqlMemberRepository.findByName("사용자1"));
    // System.out.println(jpqlMemberRepository.findByAgeGreaterThan(10));
    // System.out.println(jpqlMemberRepository.findByTeam(Team.builder().id(3L).build()));

    // List<Object[]> result = jpqlMemberRepository.aggregate();
    // for (Object[] objects : result) {
    // System.out.println(Arrays.toString(objects));
    // System.out.println("인원 수 : " + objects[0]);
    // System.out.println("나이 합계 : " + objects[1]);
    // System.out.println("나이 평균 : " + objects[2]);
    // System.out.println("연장자 : " + objects[3]);
    // System.out.println("최연소 : " + objects[4]);
    // }

    // System.out.println(jpqlMemberRepository.findByTeam2("team2"));

    List<Object[]> result = jpqlMemberRepository.findByTeam5("team1");
    for (Object[] objects : result) {
      System.out.println(Arrays.toString(objects));
      // System.out.println("member" + objects[0]);
      // System.out.println("team" + objects[1]);
    }
  }

  // TODO: Querydsl 테스트
  @Test
  public void queryDslTest() {
    // TODO: new 하는 과정
    QProduct qProduct = QProduct.product;
    QJpqlMember qJpqlMember = QJpqlMember.jpqlMember;
    // TODO: 조건을 연쇄적으로 여러 개 작성할 때 사용 하는 BooleanBuilder
    BooleanBuilder booleanBuilder = new BooleanBuilder(); // import com.querydsl.core.BooleanBuilder;

    // TODO: 상품명이 제품 1인 상품 조회
    // Iterable<Product> products =
    // productRepository.findAll(qProduct.name.eq("제품1"));

    // //TODO: 상품명이 제품1 이고(equals = eq), 가격이 500을 초과(GreaterThan = gt)인 상품 조회
    // Iterable<Product> products =
    // productRepository.findAll(qProduct.name.eq("제품1")
    // .and(qProduct.price.gt(500)));

    // TODO: 상품명이 제품1 이고(equals = eq), 가격이 500을 초과(GreaterThan = gt)인 상품 조회
    // (BooleanBuilder ver.)
    booleanBuilder.and(qProduct.name.eq("제품1")).and(qProduct.price.gt(500));
    Iterable<Product> products = productRepository.findAll(booleanBuilder);
    for (Product product : products) {
      System.out.println(product);
    }

    // //TODO: 상품명이 제품1 이고 가격이 500이상(goe)인 상품 조회
    // Iterable<Product> products =
    // productRepository.findAll(qProduct.name.eq("제품1")
    // .and(qProduct.price.goe(500)));

    // //TODO: 상품명에 '제품' 글자 들어가는(contains) 상품 조회
    // Iterable<Product> products =
    // productRepository.findAll(qProduct.name.contains("제품"));

    // // TODO: 상품명에 '제품' 글자로 시작하는(startsWith) 상품 조회
    // Iterable<Product> products =
    // productRepository.findAll(qProduct.name.startsWith("제품"));

    // // TODO: 상품명에 '3' 글자로 끝나는(endsWith) 상품 조회
    // Iterable<Product> products =
    // productRepository.findAll(qProduct.name.endsWith("3"));
    // for (Product product : products) {
    // System.out.println(product);
    // }

    // TODO: userName 이 user3인 회원 조회
    // Iterable<JpqlMember> members =
    // jpqlMemberRepository.findAll(qJpqlMember.name.eq("user3"));

    // //TODO: userName 이 user3 인 회원 조회 (id 기준으로 내림차순 정렬) (BooleanBuilder 사용)
    // booleanBuilder.and(qJpqlMember.name.eq("user3"));
    // Iterable<JpqlMember> members = jpqlMemberRepository.findAll(booleanBuilder,
    // Sort.by("id").descending());
    // for (JpqlMember jpqlMember : members) {
    // System.out.println(jpqlMember);
    // }
  }

}