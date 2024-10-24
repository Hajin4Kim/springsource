package ch1;

public class TVMain {
  public static void main(String[] args) {
    // // LgTV 사용 -> 객체생성
    // LgTv lgTv = new LgTv();

    // lgTv.setBritzSpeaker(new BritzSpeaker()); // 의존성 주입

    // lgTv.powerOn();
    // lgTv.volumeUp();
    // lgTv.volumeDown();
    // lgTv.powerOff();

    // SamsungTV samsungTV = new SamsungTV();
    // samsungTV.setBritzSpeaker(new BritzSpeaker());
    // samsungTV.powerOn();
    // samsungTV.volumeUp();
    // samsungTV.volumeDown();
    // samsungTV.powerOff();

    TV tv = new LgTv();
    // setter 접근 못함(이게맞음)
    //형변환 후 생성 => 다형성 개념 ( 부모와 자식 간의 상속 관계가 존재하는 경우 사용 가능)
    // ((SamsungTV)tv).setBritzSpeaker(new SonySpeaker());

    tv = new LgTv();
    ((LgTv)tv).setSpeaker(new BritzSpeaker()); // interface 의 이점


    tv.powerOn();
    tv.volumeUp();
    tv.volumeDown();
    tv.powerOff();
  }
}
