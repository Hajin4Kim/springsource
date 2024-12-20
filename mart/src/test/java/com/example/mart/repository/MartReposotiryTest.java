package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;
import com.example.mart.entity.item.Delivery;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.entity.product.Album;
import com.example.mart.entity.product.Book;
import com.example.mart.entity.product.Movie;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartReposotiryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private DeliveryRepository deliveryRepository;

  // Create - member
  @Test
  public void memberInsertTest() {
    memberRepository.save(Member.builder().name("user1").city("서울시").street("187-12").zipcode("15100").build());
    memberRepository.save(Member.builder().name("user2").city("경기도").street("356-11").zipcode("17100").build());
    memberRepository.save(Member.builder().name("user3").city("부산시").street("780-12").zipcode("18100").build());

    // Member member = Member.builder()
    // .name("홍길동")
    // .zipcode("ddd")
    // .city("서울시")
    // .street("용수로")
    // .build();
    // memberRepository.save(member);
  }

  // Create - item
  @Test
  public void itemInsertTest() {
    // itemRepository.save(Item.builder().name("tshirt").price(25300).quantity(15).build());
    // itemRepository.save(Item.builder().name("shoes").price(101300).quantity(25).build());
    // itemRepository.save(Item.builder().name("pants").price(333221).quantity(10).build());

    Album album = new Album();
    album.setArtist("로제");
    album.setName("아파트");
    album.setPrice(15200);
    album.setQuantity(15);
    itemRepository.save(album);

    Book book = new Book();
    book.setAuthor("한강");
    book.setIsbn("122ㄱ");
    book.setName("채식주의자");
    book.setPrice(10800);
    book.setQuantity(15);
    itemRepository.save(book);

    Movie movie = new Movie();
    movie.setActor("김태리");
    movie.setDirector("봉준호");
    movie.setName("글래디에이터2");
    movie.setPrice(25000);
    movie.setQuantity(300);
    itemRepository.save(movie);
  }

  // Create - order
  @Test
  public void orderInsertTest() {
    //
    Member member = memberRepository.findById(1L).get();
    Item item = itemRepository.findById(2L).get();

    Order order = Order.builder()
        .orderDate(LocalDateTime.now())
        .status(OrderStatus.ORDER)
        .member(member)
        .build();
    orderRepository.save(order);

    OrderItem orderItem = OrderItem.builder()
        // 무결성 제약조건(C##JPAUSER.FKFPGWS9SXLEI2G5W2RQGFWJQLT)이 위배되었습니다- 부모 키가 없습니다
        .price(200000)
        .count(2)
        .order(order)
        .item(item)
        .build();
    orderItemRepository.save(orderItem);

    // item 수량 감소 -> 구매함에 따라 quantity 물량 수 변경 update

  }

  // Create - order
  @Test
  public void orderItemInsertTest() {
    Item item = itemRepository.findById(3L).get();
    Order order = Order.builder().id(2L).build();
    orderRepository.save(order);

    OrderItem orderItem = OrderItem.builder()
        .price(50000)
        .count(4)
        .order(order)
        .item(item)
        .build();
    orderItemRepository.save(orderItem);

  }

  // R(Read) - 전체조회
  @Test
  public void memberAndItemAndOrderListTest() {
    // // 주문 내역 조회
    // // orderRepository.findAll().forEach(order -> System.out.println(order)); //
    // // Order(id=1, orderDate=2024-11-04T13:07:13.002064, status=ORDER)

    // // 주문 상세 내역 조회 - order member
    // orderRepository.findAll().forEach(order -> {
    // System.out.println(order);
    // System.out.println(order.getMember()); // Member(id=1, name=user1,
    // zipcode=15100, city=서울시, street=187-12)
    // // get주문상품 조회는 못함 (entity 쪽에서 @관계 정립을안했기 때문) => orderItem 쪽엔 되어있음
    // });

    // TODO: 주문상품 상세 조회 - order item
    orderItemRepository.findAll().forEach(orderItem -> {
      System.out.println(orderItem); // OrderItem(id=1, price=200000, count=2)
      // TODO: 상품 상세 조회
      System.out.println(orderItem.getItem()); // Item(id=2, name=shoes, price=101300, quantity=25)
      // TODO: 주문 상세 내역 조회
      System.out.println(orderItem.getOrder()); // Order(id=1, orderDate=2024-11-04T13:07:13.002064, status=ORDER)
      // TODO: 주문자 상세 조회
      System.out.println(orderItem.getOrder().getMember()); // Member(id=1, name=user1, zipcode=15100, city=서울시,
                                                            // street=187-12)
    });
  }

  // Read - 하나 조회
  @Test
  public void memberAndItemAndOrderRowTest() {

    OrderItem orderItem = orderItemRepository.findById(1L).get();

    // TODO: 주문상품 상세 조회
    System.out.println(orderItem);
    // TODO: 상품 상세 조회
    System.out.println(orderItem.getItem());
    // TODO: 주문 상세 내역 조회
    System.out.println(orderItem.getOrder());
    // TODO: 주문자 상세 조회
    System.out.println(orderItem.getOrder().getMember());
  }

  // Update - 관계@ManyToOne
  @Test
  public void memberAndItemAndOrderUpdateTest() {

    // // 주문자의 주소 변경
    // Member member = Member.builder()
    // .id(1L)
    // .name("user1")
    // // .city("서울시")
    // .street("187-12")
    // .zipcode("15100")
    // .build();
    Member member = memberRepository.findById(1L).get();
    member.setCity("111-99");

    // TODO: save(): insert or update
    // entity manager가 있어서, 현재 entity가 new 의 상태인지, 기존 entity인지 구분가능
    // => new 인 경우 insert 호출 / 기존 entity 인 경우 update 호출
    System.out.println(memberRepository.save(member));

    // 1번 주문상품의 아이템(2번 아이템) 가격 변경
    // 아이템 수량, 가격 변경
    Item item = itemRepository.findById(2L).get();
    item.setPrice(102000);
    System.out.println(itemRepository.save(item));

    // => 전체 quantity, 전체주문비용
    OrderItem orderItem = orderItemRepository.findById(1L).get();
    orderItem.setPrice(102000);
    orderItemRepository.save(orderItem);
  }

  // D(Delete)
  @Test
  public void memberAndItemAndOrderDeleteTest() {
    // 주문 취소

    // 주문 상품 취소
    orderItemRepository.deleteById(1L); // 무결성 제약조건(C##JPAUSER.FK5ODJTCYIRCY7XU4QTOLJDHKRJ)이 위배되었습니다- 자식 레코드가 발견되었습니다

    // 주문 취소
    orderRepository.deleteById(1L);
  }

  // TODO: 양방향 (단방향 2개 통로) 다대일
  // Order ==> OrderItem 객체 그래프 탐색
  @Transactional
  @Test
  public void testOrderItemList() {
    Order order = orderRepository.findById(2L).get();
    System.out.println(order);
    // Order ==> OrderItem 탐색 시도
    // TODO: fetch 전략 설정 하기!!! => ERROR .LazyInitializationException:
    order.getOrderItemsList().forEach(orderItem -> System.out.println(orderItem));
  }

  // TODO: 양방향(Member <-> Order) 다대일
  @Transactional
  @Test
  public void testOrderList() {
    Member member = memberRepository.findById(1L).get();
    System.out.println(member);

    // Member => Order 탐색 시도
    member.getOrders().forEach(order -> System.out.println(order));
  }

  // TODO: 일대일 관계 - Orders 와 Delivery
  @Test
  public void testDeliveryInsert() {

    // 배송 정보 입력 (insert)
    Delivery delivery = Delivery.builder()
        .city("서울시")
        .street("동소문로1가")
        .zipcode("11051")
        .deliveryStatus(DeliveryStatus.READY)
        .build();
    deliveryRepository.save(delivery);

    // order 와 배송정보 연결 (update)
    Order order = orderRepository.findById(2L).get();
    order.setDelivery(delivery);
    orderRepository.save(order);
  }

  @Test
  public void testOrderRead() {
    // 2L번 ORder 조회 (+ 배송정보)
    Order order = orderRepository.findById(2L).get();
    System.out.println(order);
    System.out.println(order.getDelivery());
  }

  // 양방향 (배송 => 주문)
  @Test
  public void testDeliveryRead() {
    // 배송정보 조회 (+order)
    Delivery delivery = deliveryRepository.findById(1L).get();
    System.out.println(delivery);
    System.out.println(delivery.getOrder());
  }

  // TODO: queryDSL -> QueryDslOrderRepositoryImpl
  @Test
  public void testMembers() {
    System.out.println(orderRepository.members());
  }

  @Test
  public void testItems() {
    System.out.println(orderRepository.items());
  }

  @Test
  public void testJoin() {
    List<Object[]> result = orderRepository.joinTest();

    for (Object[] objects : result) {
      System.out.println(Arrays.toString(objects));
      System.out.println((Order) objects[0]); // TODO: order 만 뽑기
      System.out.println((Member) objects[1]); // TODO: member 만 뽑기
      System.out.println((OrderItem) objects[2]); // TODO: OrderItem 만 뽑기
    }
  }

  @Test
  public void testSubQuery() {
    List<Object[]> result = orderRepository.subQueryTest();
    for (Object[] objects : result) {
      System.out.println(Arrays.toString(objects));
      System.out.println((Order) objects[0]); // TODO: order 만 뽑기
      System.out.println((Member) objects[1]); // TODO: member 만 뽑기
      System.out.println((Long) objects[2]); // TODO: OrderItem 만 뽑기
    }
  }
}
