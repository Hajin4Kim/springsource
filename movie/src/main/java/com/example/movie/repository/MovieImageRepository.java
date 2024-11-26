package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.total.MovieImageReviewRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long>, MovieImageReviewRepository {

  // movie_mno 로 movie_image 까지 제거하는 메소드 생성 (자식삭제 부모삭제)
  @Modifying
  @Query("DELETE FROM MovieImage mi WHERE mi.movie = :movie")
  void deleteByMovie(Movie movie);
}
