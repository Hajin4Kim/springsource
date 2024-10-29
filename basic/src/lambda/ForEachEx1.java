package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForEachEx1 {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("사과", "딸기", "수박", "바나나", "배", "멜론");

    // List<String> list1 = new ArrayList<>();
    // list1.add("사과");
    // list1.add("딸기");
    // list1.add("수박");
    // list1.add("바나나");

    for (String s : list) {
      System.out.println(s);
    }

    // TODO: forEach
    // TODO: list.forEach(Consumer<? super String> action);
    // <? super String> : 무조건 String타입 혹은 조상타입
    // Consumer<String> c = (x) -> System.out.println(x);
    // c.accept("")

    // TODO: (외우기)하나씩 꺼내서 출력하는 용도로 주로 사용
    list.forEach((x) -> System.out.println(x));
  }
}
