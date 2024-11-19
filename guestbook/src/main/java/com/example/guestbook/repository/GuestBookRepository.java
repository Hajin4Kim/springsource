package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {

  default Predicate makePredicate(String type, String keyword) {
    QGuestBook qGuestBook = QGuestBook.guestBook;

    BooleanBuilder builder = new BooleanBuilder();
    // TODO: gno > 0 : range scan
    builder.and(qGuestBook.gno.gt(0L));
    if (type == null)
      return builder;

    // TODO: content like '%검색어%' or title like '%검색어%' writer like '%검색어%'
    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if (type.contains("c")) { // 카테고리 검색시
      conditionBuilder.or(qGuestBook.content.contains(keyword)); // TODO:객체리스트탐색 이용
    }
    if (type.contains("t")) { // 제목 검색시
      conditionBuilder.or(qGuestBook.title.contains(keyword));
    }
    if (type.contains("w")) { // 작성자 검색시
      conditionBuilder.or(qGuestBook.writer.contains(keyword));
    }
    // TODO: 최종 : gno > 0 and (content like '%검색어%' or title like '%검색어%' writer
    // like '%검색어%' )
    builder.and(conditionBuilder);

    return builder;
  }
}