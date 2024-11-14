package com.example.guestbook.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class GuestBookServiceImpl implements GuestBookService {

  // TODO: repository 와 @RequiredArgsConstructor 짝궁
  private final GuestBookRepository guestBookRepository;

  @Override
  public Long register(GuestBookDto dto) {
    return guestBookRepository.save(dtoToEntity(dto)).getGno();
  }

  @Override
  public GuestBookDto read(Long gno) {
    // GuestBook result = guestBookRepository.findById(gno).get();
    // GuestBookDto dto = entityToDto(result);
    // return dto;
    // TODO: 위의 3줄을 아래의 한줄로 표현
    return entityToDto(guestBookRepository.findById(gno).get());
  }

  @Override
  public PageResultDto<GuestBookDto, GuestBook> list(PageRequestDto requestDto) {
    Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());

    // TODO: Predicate predicate(이거는 BooleanBuilder 사용), Pageable pageable
    Page<GuestBook> result = guestBookRepository
        .findAll(guestBookRepository.makePredicate(requestDto.getType(), requestDto.getKeyword()), pageable);

    Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));

    return new PageResultDto<>(result, fn);

  }

  @Override
  public Long update(GuestBookDto dto) {
    GuestBook guestBook = guestBookRepository.findById(dto.getGno()).get();
    guestBook.setTitle(dto.getTitle());
    guestBook.setContent(dto.getContent());
    return guestBookRepository.save(guestBook).getGno();
  }

  @Override
  public void delete(Long gno) {
    guestBookRepository.deleteById(gno);
  }

}
