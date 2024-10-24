package ch1;

public class SamsungTV implements TV {

  // 멤버변수(== 인스턴스 변수, 필드, 특성, 속성)
  private Speaker speaker; // 객체타입 초기화 is null => 객체생성 해야함 (생성자 이용)

  // // 생성자 (constructors)
  // public LgTv(BritzSpeaker britzSpeaker) {
  //   this.britzSpeaker = britzSpeaker;
  // }

  // @Setter
  public void setBritzSpeaker(Speaker speaker) {
    this.speaker = speaker;
  }

  @Override
  public void powerOn() {
    System.out.println("SamsungTV - 전원 On");
  }

  @Override
  public void powerOff() {
    System.out.println("SamsungTV - 전원 Off");
  }

  @Override
  public void volumeUp() {
    // System.out.println("SamsungTV - 볼륨 Up");
    speaker.volumeUp(); // Exception in thread "main" java.lang.NullPointerException: Cannot invokke "ch1.BritzSpeaker.volumeUp()" because "this.britzSpeaker" is null
  }

  @Override
  public void volumeDown() {
    // System.out.println("SamsungTV - 볼륨 Down");
    speaker.volumeDown();
  }
  
}
