package com.example.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.service.MovieService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/upload")
public class UploadController {

  @Value("${com.example.movie.upload.path}")
  private String uploadPath;

  @GetMapping("/upload")
  public void getUpload() {
    log.info("file upload 폼 요청");

  }

  @PostMapping("/upload")
  public void postUpload(MultipartFile[] uploadFiles) {

    for (MultipartFile multipartFile : uploadFiles) {
      log.info("getOriginalFilename {}", multipartFile.getOriginalFilename());
      log.info("getSize {}", multipartFile.getSize());
      log.info("getContentType {}", multipartFile.getContentType()); // image/png

      // TODO: 이미지 파일 여부 확인 => image 가 아니면 돌려보내기
      if (!multipartFile.getContentType().startsWith("image")) {
        return;
      }

      // 사용자가 올린 파일명
      String originName = multipartFile.getOriginalFilename();

      // // 2024-11-26 // 년/월/일 폴더 가져옴
      String saveFolderPath = makeFolder();

      // TODO: 업로드된 파일 저장 => uuid(중복파일명 처리)
      String uuid = UUID.randomUUID().toString();

      // upload/2024/11/26/uuid_originName
      String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + originName;

      Path savePath = Paths.get(saveName);

      try {
        // TODO: 폴더에 업로드 파일 저장하는 최종 코드
        multipartFile.transferTo(savePath);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

  }

  private String makeFolder() {

    // 오늘날짜 구하기
    LocalDate today = LocalDate.now();
    log.info("today {}", today); // 2024-11-26
    String dateStr = today.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));

    File dirs = new File(uploadPath, dateStr);
    if (!dirs.exists()) {
      dirs.mkdirs(); // 실제 폴더 생성
    }

    // 폴더구조 : / or \\
    // c:/upload/1.jpg or c:\\upload\\1.jpg

    // // 날짜나 시간, 숫자 특정 포멧 지정해 보고 싶다? => Formatter
    // SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
    // sdf.format(new Date());

    // 오늘날짜로 폴더 생성하기
    return dateStr;
  }

}
