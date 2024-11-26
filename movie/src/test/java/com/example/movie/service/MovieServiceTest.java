package com.example.movie.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.dto.MovieDto;

@SpringBootTest
public class MovieServiceTest {

  @Autowired
  private MovieService movieService;

  @Test
  public void testGetRow() {
    MovieDto movieDto = movieService.get(4L);
    System.out.println(movieDto);
    /*
     * MovieDto(mno=4, title=Movie 4, movieImageDtos=[MovieImageDto(inum=8,
     * uuid=d3b26065-c317-4c84-b8a6-c1eb4233c8f3, imgName=test2.jpg, path=null,
     * regDate=null, updateDate=null), MovieImageDto(inum=7,
     * uuid=7b97163d-62fd-49a8-b2e3-71db1691a149, imgName=test1.jpg, path=null,
     * regDate=null, updateDate=null), MovieImageDto(inum=6,
     * uuid=371d5fc4-ca91-41df-888c-beed2e756de9, imgName=test0.jpg, path=null,
     * regDate=null, updateDate=null)], reviewAvg=5.0, reviewCnt=1,
     * regDate=2024-11-26T10:16:06.583793, updateDate=null)
     */
  }
}
