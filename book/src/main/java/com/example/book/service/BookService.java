package com.example.book.service;

import java.util.List;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

public interface BookService {

  // TODO: CRUD
  Long create(BookDto dto); // TODO: Create

  BookDto getRow(Long id); // TODO: Read one

  PageResultDto<BookDto, Book> getList(PageRequestDto requestDto);// TODO: Read ALl //TODO: Page나누기 개념 추가

  Long update(BookDto dto); // TODO: update

  void delete(Long id); // TODO: delete

  List<CategoryDto> getCateList();// TODO: selectbox 카테고리명

  List<PublisherDto> getPubList();// TODO: selectbox 출판사명

  // TODO: category entity -> dto / dto -> entity
  public default CategoryDto entityToDto(Category entity) {
    return CategoryDto.builder()
        .id(entity.getId())
        .categoryName(entity.getName())
        .build();
  }

  public default Category entityToDto(CategoryDto dto) {
    return Category.builder()
        .id(dto.getId())
        .name(dto.getCategoryName())
        .build();
  }

  // TODO: publisher entity -> dto / dto -> entity
  public default PublisherDto entityToDto(Publisher entity) {
    return PublisherDto.builder()
        .id(entity.getId())
        .publisherName(entity.getName())
        .build();
  }

  public default Category entityToDto(PublisherDto dto) {
    return Category.builder()
        .id(dto.getId())
        .name(dto.getPublisherName())
        .build();
  }

  // TODO: entity -> DTO
  public default BookDto entityToDto(Book entity) {
    return BookDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .writer(entity.getWriter())
        .categoryName(entity.getCategory().getName())
        .publisherName(entity.getPublisher().getName())
        .price(entity.getPrice())
        .salePrice(entity.getSalePrice())
        .createdDateTime(entity.getCreatedDateTime())
        .lastModifiedDateTime(entity.getLastModifiedDateTime())
        .build();
  }

  // TODO: DTO -> entity
  public default Book dtoToEntity(BookDto dto) {
    return Book.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .writer(dto.getWriter())
        .price(dto.getPrice())
        .salePrice(dto.getSalePrice())
        .category(Category.builder().id(Long.parseLong(dto.getCategoryName())).build())
        .publisher(Publisher.builder().id(Long.parseLong(dto.getPublisherName())).build())
        .build();
  }
}
