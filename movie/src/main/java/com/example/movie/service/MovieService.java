package com.example.movie.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {

  // 영화목록 (페이지나누기 + 검색)
  PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto);

  // 영화등록
  Long register(MovieDto movieDto);

  // 영화수정
  Long modify(MovieDto movieDto);

  // 영화 삭제
  void delete(Long mno);

  // 영화 상세조회
  MovieDto get(Long mno);

  default MovieDto entityToDto(Movie movie, List<MovieImage> movieImages, Long reviewCnt, Double reviewAvg) {

    // movie => movieDto
    MovieDto movieDto = MovieDto.builder()
        .mno(movie.getMno())
        .title(movie.getTitle())
        // .movieImageDtos(movieImages.get())
        .reviewCnt(reviewCnt)
        .reviewAvg(reviewAvg)
        .regDate(movie.getRegDate())
        .build();

    // MovieImage => MovieImageDto 변경 후 리스트 작업
    List<MovieImageDto> movieImageDtos = movieImages.stream().map(movieImage -> {
      return MovieImageDto.builder()
          .inum(movieImage.getInum())
          .uuid(movieImage.getUuid())
          .imgName(movieImage.getImgName())
          .path(movieImage.getPath()).build();
    }).collect(Collectors.toList());
    movieDto.setMovieImageDtos(movieImageDtos);

    return movieDto;
  }

  default Map<String, Object> dtoToEntity(MovieDto movieDto) {
    return null;
  }
}
