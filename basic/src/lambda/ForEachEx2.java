package lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class ForEachEx2 {
  public static void main(String[] args) {
    List<Student> list = Arrays.asList(new Student("홍길동", 90, 96),
        new Student("김수정", 80, 85));

    // 향상된 for
    for (Student student : list) {
      System.out.println(student.getName() + " : 영어 " + student.getEng() + " 점, 수학 " + student.getMath());
    }

    list.forEach((student) -> {
      System.out.println(student.getName() + " : 영어 " + student.getEng() + " 점, 수학 " + student.getMath());

    });

    Map<String, Integer> items = new HashMap<>();
    items.put("A", 10);
    items.put("B", 20);
    items.put("C", 30);
    items.put("D", 40);
    items.put("E", 50);

    System.out.println(items.get("A")); // 10 // items.get(키값) -> value 받을 수 있음
    for (Map.Entry<String, Integer> entry : items.entrySet()) {
      System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
    }
    System.out.println(items); // {A=10, B=20, C=30, D=40, E=50}

    // List, Set, Map
    // List: 데이터 중복 가능, 차례대로 접근, => 향상된 for 접근 가능
    // Set : (=집합)중복 안됨, 순서의 의미 없음 => 향상된 for 접근 가능
    // Map : key 값만 중복 안됨, == TODO: 향상된 for 안됨

    // 각 자료구조에 대한 접근하는 방법에 대한 통일성을 부여 TODO:==> Iterator
    Set<String> keySet = items.keySet();
    Iterator<String> keyIterator = keySet.iterator();
    while (keyIterator.hasNext()) {
      String key = keyIterator.next();
      Integer value = items.get(key);
      System.out.println("Item : " + key + " Count : " + value);
    }
    /*
     * Item : A Count : 10
     * Item : B Count : 20
     * Item : C Count : 30
     * Item : D Count : 40
     * Item : E Count : 50
     */

    // 결국 이것을 향상된 for each 돌리면 훨씬 간단해짐
    // TODO: items.forEach(BiConsumer<? super String,? super Integer> action);
    items.forEach((k, v) -> {
      System.out.println("Item : " + k + " Count : " + v);
    });

  }
}
