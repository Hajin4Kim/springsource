package ch1;

public class LgTv implements TV {

  /*
   * 변수
   * 1) 멤버 변수 (Instance) : 클래스 안에 선언된 변수
   * 객체인 경우 null 로 초기화 됨
   * 기본타입 변수(float, double, int, char, boolean)인 경우: 0 or '' or 0.0 -> false 로 초기화
   * 됨
   * 
   * 2) 지역 변수 (Public/Private): 메소드나 {} 안에 선언된 변수
   * 3) 매개 변수 (Parameter) : powerOn(int x) 에서 x를 부를때
   * 
   */

  // 멤버변수(== 인스턴스 변수, 필드, 특성, 속성)
  private Speaker speaker; // 객체타입 초기화 is null => 객체생성 해야함 (생성자 이용)

  // // 생성자 (constructors)
  // public LgTv(BritzSpeaker britzSpeaker) {
  // this.britzSpeaker = britzSpeaker;
  // }

  // @Setter
  public void setSpeaker(Speaker speaker) {
    this.speaker = speaker;
  }

  @Override
  public void powerOn() {
    System.out.println("LgTV - 전원 On");
  }

  @Override
  public void powerOff() {
    System.out.println("LgTV - 전원 Off");
  }

  @Override
  public void volumeUp() {
    // System.out.println("LgTV - 볼륨 Up");
    speaker.volumeUp(); // Exception in thread "main" java.lang.NullPointerException: Cannot invokke
                        // "ch1.BritzSpeaker.volumeUp()" because "this.britzSpeaker" is null
  }

  @Override
  public void volumeDown() {
    // System.out.println("LgTV - 볼륨 Down");
    speaker.volumeDown();
  }

}
