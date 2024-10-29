package lambda;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 함수형 인터페이스 @FunctionalInterface
// 메소드는 프로토타입이 거의 일정하기 때문에 자주 쓰이는 형식의 메소드를 가지고 있는
//TODO: 함수형 인터페이스 선언 가능 => java.util.function 패키지에 정의되어있음

public class LambdaEx4 {
  public static void main(String[] args) {
    Supplier<Integer> s = () -> (int) (Math.random() * 100) + 1;
    System.out.println("1 ~ 100 사이 임의의 수 :" + s.get());

    // System.out.println(LocalDateTime.now());
    Supplier<LocalDateTime> s1 = () -> LocalDateTime.now();
    System.out.println("오늘날짜시간 : " + s1.get());

    Consumer<Integer> c = (i) -> System.out.println(i * i);
    c.accept(5); // TODO: 호출 accept

    // String => 화면 출력
    Consumer<String> c1 = (str) -> System.out.println(str);
    c1.accept("안녕하세요");

    // 오늘 날짜 / 시간 출력
    Consumer<LocalDateTime> c2 = (date) -> System.out.println(date);
    c2.accept(LocalDateTime.now());

    // TODO: 들어가고 나가는 타입이 같거나 다른 function
    Function<Integer, Integer> f1 = (i) -> i * i;
    System.out.println(f1.apply(10));

    // 일의 자리 없앤 후 리턴
    f1 = (i) -> i / 10 * 10;
    System.out.println(f1.apply(561));

    // 문자 78 을 숫자 78 로 바꾸기
    // Function<String, Integer> f2 = (i) -> Integer.parseInt(i);
    // Function<String, Integer> f2 = (i) -> Integer.valueOf(i);
    Function<String, Integer> f2 = Integer::parseInt; // TODO: 하나 들어온거 i 밖에 없으니 생략 (String 은 안됨)
    System.out.println(f2.apply("78"));

    // TODO: Predicate
    // T 또느 F 로 결과 도출되는 Predicate (w/삼항연산자)
    Predicate<Integer> p1 = (i) -> i > 10;
    System.out.println(p1.test(25) ? "10보다 큼" : "10 보다 작음");

    // 문자열의 길이가 6자리보다 크냐?
    Predicate<String> p2 = (str) -> str.length() > 6;
    System.out.println(p2.test("abcde") ? "문자열 길이 6자리 초과" : "문자열 길이 6자리 미만");

    // 문자열에 대문자 A 포함여부
    p2 = (str) -> str.contains("A");
    System.out.println(p2.test("abcAefg") ? "A 가 포함됨" : "A 가 포함되지 않음");

    // TODO: BiFunction -> FUnction 과 사용법 동일하지만 받는게 하나 더 있을 뿐
    // BiFunction<첫번째값, 두번째값, 나올타입>
    BiFunction<Integer, Integer, Integer> function = (x, y) -> x + y;
    System.out.println(function.apply(5, 3));

    function = (x, y) -> x > y ? x : y; // x > y 이 t 면 x 반환, f 면 y 반환
    System.out.println(function.apply(5, 3));

    // 두 개의 String 받아, 연결 후 return
    BiFunction<String, String, String> function1 = (s3, s4) -> s3.concat(s4); // s3 + s4
    System.out.println(function1.apply("java8", "람다"));
  }
}
