package com.example.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.dto.UploadResultDto;
import com.example.movie.service.MovieService;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
  public ResponseEntity<List<UploadResultDto>> postUpload(MultipartFile[] uploadFiles) {
    // TODO: 저장된 파일 정보 추가
    List<UploadResultDto> uploadResultDtos = new ArrayList<>();

    for (MultipartFile multipartFile : uploadFiles) {
      log.info("getOriginalFilename {}", multipartFile.getOriginalFilename());
      log.info("getSize {}", multipartFile.getSize());
      log.info("getContentType {}", multipartFile.getContentType()); // image/png

      // TODO: 이미지 파일 여부 확인 => image 가 아니면 돌려보내기
      if (!multipartFile.getContentType().startsWith("image")) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

        // TODO: 썸네일 저장 (+ 크기 조절)
        String thumbSaveName = uploadPath + File.separator + saveFolderPath + File.separator + "s_" + uuid + "_"
            + originName;
        File thumbFile = new File(thumbSaveName);

        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 100, 100);

      } catch (Exception e) {
        e.printStackTrace();
      }

      // TODO: 저장된 파일 정보 추가
      uploadResultDtos.add(new UploadResultDto(uuid, originName, saveFolderPath));
    }
    return new ResponseEntity<List<UploadResultDto>>(uploadResultDtos, HttpStatus.OK);

  }

  // TODO: 업로드한 파일 이미지 띄우기
  @GetMapping("/display")
  public ResponseEntity<byte[]> getFile(String fileName, String size) { // TODO: size 변수로 썸네일용인지, 모달용인지 확인

    ResponseEntity<byte[]> result = null;

    try {
      // TODO: fileName = 내용 decode 해서 파일에 저장해줘야 한다.
      // http://localhost:8080/upload/display?fileName=2024%2F11%2F27%5Cca247bdf-671c-4ff9-9e48-7ab7c292f7ee_rebound2.jpg
      String srcFileName = URLDecoder.decode(fileName, "utf-8");
      File file = new File(uploadPath + File.separator + srcFileName);

      if (size != null && size.equals("1")) {
        // upload/2024/11/27/, =>substring 해서 원본파일명 받기
        file = new File(file.getParent(), file.getName().substring(2));
      }

      // TODO: 브라우저에 딸려보내는 코드 header
      HttpHeaders headers = new HttpHeaders();
      // TODO: Files.probeContentType(file.toPath())); 이 코드는 Content-Type : image/png
      // 인지 text/html ... 무엇인지 알려주는 코드
      headers.add("Content-Type", Files.probeContentType(file.toPath()));
      result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return result;
  }

  // TODO: 업로드 이미지 삭제
  @PostMapping("/remove")
  public ResponseEntity<String> postRemove(String filePath) {
    log.info("업로드 이미지 삭제요청 {}", filePath);

    try {
      // decode 하기
      String srcFileName = URLDecoder.decode(filePath, "utf-8");

      // 원본 파일 삭제
      File file = new File(uploadPath, srcFileName);
      file.delete();

      // 썸네일 파일 삭제
      // upload/2024/11/27~~~~1.jpg

      // TODO: .getParent() 는 upload/2024/11/27 이 부분을 갖고옴
      File thumbFile = new File(file.getParent(), "s_" + file.getName());
      thumbFile.delete();

      return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
