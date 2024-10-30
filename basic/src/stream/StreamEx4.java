package stream;

import java.util.List;
import java.util.stream.IntStream;

public class StreamEx4 {
  static List<String> list; // TODO: 멤버변수 => new 안하면 NullPointerException

  public static void main(String[] args) {
    IntStream stream = IntStream.rangeClosed(1, 10);
    // 2의 배수 갯수
    long count = stream.filter(i -> i % 2 == 0).count();
    System.out.println("2의 배수 갯수 : " + count);

    // 스트림종료 -> 다시 stream 선언
    stream = IntStream.rangeClosed(1, 10);
    System.out.println("2의 배수 합 : " + stream.filter(i -> i % 2 == 0).sum());

    stream = IntStream.rangeClosed(1, 10);
    System.out.println("2의 배수 평균 : " + stream.filter(i -> i % 2 == 0).average());

    stream = IntStream.rangeClosed(1, 10);
    System.out.println("2의 배수 최대값 : " + stream.filter(i -> i % 2 == 0).max());

    stream = IntStream.rangeClosed(1, 10);
    System.out.println("2의 배수 최소값 : " + stream.filter(i -> i % 2 == 0).min());

    // // TODO: Optional : NullPointerException
    // if (list != null) {
    // System.out.println(list.size());
    // }

  }
}
