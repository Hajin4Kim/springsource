package stream;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lambda.Student;

public class StreamEx3 {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("바둑", "바나나", "포도", "딸기", "바질", "강아지");

    // "바" 로 시작하는 문자 출력 (foreach ver.)
    for (String s : list) {
      if (s.startsWith("바")) {
        System.out.println(s);
      }
    }
    System.err.println("------------------------------");
    // TODO: Stream 으로 하는 경우 -> .filter(조건)
    // TODO: 1. 스트림 변환 => 2. 연산 => 3. 출력
    // list.stream().filter(Predicate<? super String> predicate)
    list.stream().filter(b -> b.startsWith("바")).collect(Collectors.toList()).forEach(System.out::println);

    List<Student> list2 = Arrays.asList(new Student("홍길동", 90, 96),
        new Student("송혜교", 80, 85), new Student("김희원", 80, 85), new Student("김수정", 80, 85));

    // 성이 "김" 으로 시작하는 학생의 이름 출력 (forEach ver.)
    for (Student stu : list2) {
      if (stu.getName().startsWith("김")) {
        System.out.println(stu.getName());
      }
    }
    // Stream 버전으로
    list2.stream().filter(stu -> stu.getName().startsWith("김")).forEach(System.out::println);

    // 성이 "김" 으로 시작하는 학생들만 새로운 리스트에 담고 출력
    // TODO: stream().map().filter().collect().foreach()
    list2.stream()
        .map(s -> s.getName()) // 이름만 모으기
        .filter(stu -> stu.startsWith("김")) // 김 필터링
        .collect(Collectors.toList()) // 리스트로 수집
        .forEach(System.out::println); // 출력
    System.out.println("--------------------------------");
    list2.stream()
        .filter(stu -> stu.getName().startsWith("김")) // 김 으로 시작하는 학생 필터링
        .peek(s -> System.out.println(s)) // TODO: 중간에 결과 들여다보기
        .map(s -> s.getName()) // 이름만 모으기
        .collect(Collectors.toList()) // 리스트로 수집
        .forEach(System.out::println); // 출력

    // 짝수만 출력
    IntStream.rangeClosed(1, 10).filter(i -> (i % 2 == 0)).forEach(System.out::println);

    // TODO: distinct() : 중복제거
    list = Arrays.asList("바둑", "바나나", "포도", "딸기", "바질", "강아지", "바둑", "바나나");
    list.stream().distinct().forEach(System.out::println);

    // TODO: 파일 확장자를 추출하고 중복 제거한 후 출력
    File[] files = { new File("file1.txt"),
        new File("file2.txt"), new File("Ex1"),
        new File("Ex2.bak"), new File("test.java") };

    // TODO: 1. 스트림 변환 => 중간변환 (1. 이름모으기 2. 확장자만 추출 3. 중복 제거) => 2. 최종연산 -> 3. 출력
    Arrays.stream(files)
        .map(f -> f.getName())
        .peek(f -> System.out.println(f)) // TODO: peek 로 중간중간 확인하기
        .filter(s -> s.contains(".")) // 확장자 여부 확인 -> 확장자가 있는 파일명만
        .map(n -> n.substring(n.lastIndexOf(".") + 1)) // 확장자만 수집
        .distinct()// 중복제거
        .forEach(System.out::println);

    // for (File f : files) {
    // if (f.getName().contains(".")) {
    // files.substring(files.lastIndexOf("."+1)).distinct();
    // }else {System.out.println("중복없음");}
    // System.out.println();
    // }
  }
}
