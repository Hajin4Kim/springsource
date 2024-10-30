package com.example.project2.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Item;
import com.example.project2.entity.constant.ItemStatus;

@SpringBootTest
public class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void insertTest() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Item item = Item.builder()
          .itemNm("운동화" + i)
          .price(95000)
          .stockNumber(15)
          .itemSellStatus(ItemStatus.SELL)
          .regTime(LocalDateTime.now())
          .build();
      itemRepository.save(item);
    });
  }

  @Test
  public void selectOneTest() {
    // id 가 5 인 운동화 조회
    System.out.println(itemRepository.findById(5L).get());
  }

  @Test
  public void selectAllTest() {
    // 전체 운동화 조회
    itemRepository.findAll().forEach(item -> System.out.println(item));
  }

  @Test
  public void updateTest() {
    // 운동화 아이디가 6인 정보 업데이트
    // 가격, updateTime 수정
    Item item = itemRepository.findById(6L).get();
    item.setPrice(444444);
    item.setUpdateTime(LocalDateTime.now());
    itemRepository.save(item);
  }

  @Test
  public void deleteTest() {
    // id 가 9 인 운동화 삭제
    itemRepository.deleteById(9L);
  }

}
