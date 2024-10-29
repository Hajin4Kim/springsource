package lambda;

// TODO: public 클래스는 하나만 존재할 수 있음

//TODO: 람다식 : 메소드를 하나의 식으로 표현
//    1) return타입, 메소드명 제거
//            (int a, int b) -> {return a > b ? a : b;}
//    2) return 구문 생략 가능 (실행할 구문이 하나인 경우 / {} 생략)
//            (int a, int b) -> a > b ? a : b;
//    3) 매개변수가 추측이 가능한 상태라면 타입 생략 가능
//            (a, b) -> a > b ? a : b;

class Lambda1 {
  // 화살표
  int max(int a, int b) {
    return a > b ? a : b; // a > b 해서 이 구문이 T 면 a return , F 면 b
  }

  int square(int x) {
    return x * x;
  }
  // (x) -> x * x;

  int roll() {
    return (int) (Math.random() * 6);
  }
  // () -> (int) (Math.random()*6);

  int sumArr(int[] arr) {
    int sum = 0;
    for (int i : arr) {
      sum += i;
    }
    return sum;
  }
  // (int[] arr) -> {내부 내용 동일}

}

public class LambdaEx1 {
  public static void main(String[] args) {
    Lambda1 obj = new Lambda1();
    System.out.println(obj.max(6, 3));

  }
}
