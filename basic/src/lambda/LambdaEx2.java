package lambda;

// interface 선언
// new 를 직접적으로 할 수 없음

// >> new를 못하면 어떻게 사용?
// 1) 구현 클래스 사용
// 2) 익명 구현 클래스 사용

@FunctionalInterface // 일반 메소드(static, default는 가능)가 2개 이상 들어오는 것을 compile 단계에서 체크
interface MyFunctionalInterface1 {
  void method();

  // void print(); // 일반메소드 2개째 -> 에러발생
  static void print() {
  }

  default void print1() {
  }
}

// TODO: 1) 구현 클래스 사용 A
// class A implements MyFunctionalInterface1 {
// @Override
// public void method(){
// }
// }

// TODO: 2) 익명 구현 클래스 사용
public class LambdaEx2 {
  public static void main(String[] args) {
    // MyFunctionalInterface1 obj = new MyFunctionalInterface1() {
    // @Override
    // public void method() {
    // System.out.println("인터페이스 구현");
    // }

    // }; // 세미콜론 중요 -> 익명 구현
    // obj.method();

    // TODO: 익명구현객체를 Lambda식으로 작성 (매우 간단해짐)
    MyFunctionalInterface1 obj = () -> System.out.println("인터페이스 구현");
    obj.method();

    obj = () -> {
      int i = 10;
      System.out.println(i * i);
    };
    obj.method();
  }
}
