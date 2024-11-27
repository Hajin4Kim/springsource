package com.example.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

  private final MovieImageRepository movieImageRepository;
  private final MovieRepository movieRepository;
  private final ReviewRepository reviewRepository;

  @Override
  public PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto) {

    Pageable pageable = pageRequestDto.getPageable(Sort.by("mno").descending());

    Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageable);

    Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
        (List<MovieImage>) Arrays.asList((MovieImage) en[1]),
        (Long) en[2], (Double) en[3]));

    return new PageResultDto<>(result, function);
  }

  @Override
  public Long register(MovieDto movieDto) {

    Map<String, Object> entityMap = dtoToEntity(movieDto);

    Movie movie = (Movie) entityMap.get("movie");
    List<MovieImage> movieImages = (List<MovieImage>) entityMap.get("movieImages");

    movieRepository.save(movie);
    movieImages.forEach(movieImage -> movieImageRepository.save(movieImage));

    return movie.getMno();
  }

  @Override
  public Long modify(MovieDto movieDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'modify'");
  }

  @Transactional
  @Override
  public void delete(Long mno) {
    Movie movie = Movie.builder().mno(mno).build();

    movieImageRepository.deleteByMovie(movie);
    reviewRepository.deleteByMovie(movie);
    movieRepository.delete(movie);
  }

  @Override
  public MovieDto get(Long mno) {
    List<Object[]> result = movieImageRepository.getMovieRow(mno);

    Movie movie = (Movie) result.get(0)[0];
    Long reviewCnt = (Long) result.get(0)[2];
    Double avg = (Double) result.get(0)[3];

    // 1 : 영화이미지
    List<MovieImage> movieImages = new ArrayList<>();
    result.forEach(row -> {
      MovieImage movieImage = (MovieImage) row[1];
      movieImages.add(movieImage);
    });

    return entityToDto(movie, movieImages, reviewCnt, avg);

    /*
     * [Movie(mno=4, title=Movie 4), MovieImage(inum=8,
     * uuid=d3b26065-c317-4c84-b8a6-c1eb4233c8f3, imgName=test2.jpg, path=null), 1,
     * 5.0]
     */
    /*
     * [Movie(mno=4, title=Movie 4), MovieImage(inum=7,
     * uuid=7b97163d-62fd-49a8-b2e3-71db1691a149, imgName=test1.jpg, path=null),
     * 1,5.0]
     */
    /*
     * [Movie(mno=4, title=Movie 4), MovieImage(inum=6,
     * // uuid=371d5fc4-ca91-41df-888c-beed2e756de9, imgName=test0.jpg, path=null),
     * 1,
     * 5.0]
     */
  }

}
