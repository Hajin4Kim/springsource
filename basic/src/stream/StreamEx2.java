package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx2 {
  public static void main(String[] args) {
    // Stream<String> stream = Stream.of("사과", "포도", "귤", "바나나", "수박");

    // 배열버전
    File[] files = { new File("file1.txt"),
        new File("file2.txt"), new File("file3.txt"), new File("file4.txt") };
    // 파일 이름 출력 foreach
    for (File file : files) {
      System.out.print(file.getName() + "\t");
    }

    // TODO: 파일명 추출 후 리스트에 담기
    List<String> fList = new ArrayList<>();
    for (File file : files) {
      fList.add(file.getName());
    }
    System.out.println(fList);

    // TODO: 스트림으로
    Stream<File> stream = Stream.of(new File("file1.txt"),
        new File("file2.txt"), new File("file3.txt"), new File("file4.txt"));

    /*
     * TODO: map() : 스트림 요소에 저장된 값 중에서 원하는 필드만 추출하거나
     * 특정 형태로 변환하는 경우 사용
     */
    // stream.map(Function<? super File,? extends R> mapper)
    Stream<String> fNames = stream.map(f -> f.getName()); // [file1.txt,file2.txt, file3.txt, file4.txt]
    fNames.forEach(n -> System.out.println(n));

    // TODO: .collect(Collectors)
    // IllegalStateException: stream has already been operated upon or closed
    // //TODO: 스트림은 일회용이다.
    List<String> list = stream.map(f -> f.getName()).collect(Collectors.toList());
    System.out.println(list);

    // //TODO: Stream.map.collect.foreach 로 이렇게 간단하게 작성 가능
    // stream.map(f -> f.getName()).collect(Collectors.toList())
    // .forEach(name -> System.out.println(name));

    // TODO: list2 값들을 대문자로 변경 후, 새로운 리스트(uppList)로 생성 출력
    List<String> list2 = Arrays.asList("abc", "def", "melon", "text", "apple");

    List<String> uppList = new ArrayList<>();
    list2.forEach(s -> uppList.add(s.toUpperCase()));
    System.out.println(uppList);

    // 동일한 작업을 STREAM 으로 하는 경우:
    // TODO: 스트림변환 => 연산 => 출력
    list2.stream().map(s -> s.toUpperCase())
        .collect(Collectors.toList()).forEach(System.out::println);
  }
}
