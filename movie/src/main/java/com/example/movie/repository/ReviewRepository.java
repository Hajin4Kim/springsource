package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  // movie_mno 로 review 까지 제거하는 메소드 생성 (자식삭제 부모삭제)
  @Modifying
  @Query("DELETE FROM Review r WHERE r.movie = :movie")
  void deleteByMovie(Movie movie);
}
