package com.example.movie.respository;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MovieRepositoryTest {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private MovieImageRepository movieImageRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  // @Transactional
  @Test
  public void testMovieInsert() {
    IntStream.rangeClosed(1, 50).forEach(i -> {
      Movie movie = Movie.builder().title("Movie " + i).build();
      movieRepository.save(movie);

      // 임의의 숫자 1~5 생성
      int count = (int) (Math.random() * 5) + 1;

      for (int j = 0; j < count; j++) {
        MovieImage movieImage = MovieImage.builder()
            .uuid(UUID.randomUUID().toString())
            .imgName("test" + j + ".jpg")
            .movie(movie)
            .build();

        movieImageRepository.save(movieImage);
      }

    });
  }

  @Test
  public void testListPage() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

    Page<Object[]> result = movieRepository.getListPage(pageRequest);

    for (Object[] objects : result) {
      System.out.println(Arrays.toString(objects));
    }
  }

  // @Commit
  @Transactional
  @Test
  public void testRemove() {
    // TODO: 삭제
    // ORA-02292: 무결성 제약조건(C##MOVIEUSER)이 위배되었습니다- 자식레코드가 발견되었습니다
    // 이렇게하면 당연히 관계가 묶여있어서 에러남 movieRepository.deleteById(50L);

    Movie movie = Movie.builder().mno(50L).build();

    movieImageRepository.deleteByMovie(movie);

    reviewRepository.deleteByMovie(movie);

    movieRepository.delete(movie);
  }

  @Commit
  @Transactional
  @Test
  public void testRemove2() {
    // TODO: 삭제
    // ORA-02292: 무결성 제약조건(C##MOVIEUSER)이 위배되었습니다- 자식레코드가 발견되었습니다
    // 이렇게하면 당연히 관계가 묶여있어서 에러남 movieRepository.deleteById(50L);

    Movie movie = movieRepository.findById(49L).get();
    movieRepository.delete(movie);
  }
}
