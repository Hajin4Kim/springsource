package com.example.movie.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  // movie_mno 로 review 까지 제거하는 메소드 생성 (자식삭제 부모삭제)
  @Modifying
  @Query("DELETE FROM Review r WHERE r.movie = :movie")
  void deleteByMovie(Movie movie);

  // movie_mno 이용하여 리뷰 가져오기
  // TODO: @EntityGraph 사용
  @EntityGraph(attributePaths = "member", type = EntityGraphType.FETCH)
  List<Review> findByMovie(Movie movie);
}
