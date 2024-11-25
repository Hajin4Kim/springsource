package com.example.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  // TODO: Movie 는 엔티티명
  @Query("SELECT m, avg(r.grade), count(distinct r) FROM Movie m LEFT JOIN Review r ON r.movie = m GROUP BY m")
  Page<Object[]> getListPage(Pageable pageable);
}
