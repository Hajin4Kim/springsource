package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
interface Lambda6 {
  void run();
}

public class LambdaEx5 {

  static void execute(Lambda6 lambda) {
    lambda.run();
  }

  static Lambda6 getRun() {

    return () -> System.out.println("함수형 인터페이스");
  }

  private static List<Student> list = Arrays.asList(new Student("홍길동", 90, 96),
      new Student("김수정", 80, 85));

  static void printJumsu(Function<Student, Integer> f) {
    for (Student student : list) {
      System.out.println(f.apply(student));
    } // 이름 출력 printName
  }

  static void pirntName(Function<Student, String> n) {
    for (Student student : list) {
      System.out.println(n.apply(student));
    }
  }

  public static void main(String[] args) {
    Lambda6 lambda = () -> System.out.println("----------Lambda6");
    lambda.run();

    // Lambda6 lambda6 = getRun();
    // execute(lambda6);
    // TODO: 매개변수 타입으로쓰이나, 함수형인터페이스타입 (구현할식)
    execute(getRun());

    // // 학생들의 영어 점수 출력 -> 함수형 인터페이스
    // Function<Student, Integer> f = (s) -> s.getEng();
    // // System.out.println(f.apply(list.get(0)));
    // // System.out.println(f.apply(list.get(1)));
    // for (Student student : list) {
    // System.out.println(f.apply(student));
    // }

    // // 수학 점수 출력
    // f = (s) -> s.getMath();
    // for (Student student : list) {
    // System.out.println(f.apply(student));
    // }

    System.out.println("영어점수");
    printJumsu((s) -> s.getEng());
    System.out.println("수학점수");
    printJumsu((s) -> s.getMath());
    System.out.println("학생이름");
    pirntName((n) -> n.getName());

  }

}