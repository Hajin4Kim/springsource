package com.example.movie.respository;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

@SpringBootTest
public class MovieImageRepositoryTest {

  @Autowired
  private MovieImageRepository movieImageRepository;

  @Test
  public void testTotalListPage() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

    Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageRequest);

    // [Movie(mno=55, title=Movie 50), MovieImage(inum=155,
    // uuid=c9b2dab0-8c99-4289-9b4b-cbaa6acbf7f0, imgName=test3.jpg, path=null), 0,
    // null]
    for (Object[] objects : result) {
      // System.out.println(Arrays.toString(objects));
      Movie movie = (Movie) objects[0];
      MovieImage movieImage = (MovieImage) objects[1];
      Long count = (Long) objects[2];
      Double avg = (Double) objects[3];

      System.out.println(movie);
      System.out.println(movieImage);
      System.out.println(count);
      System.out.println(avg);
    }
  }
}
