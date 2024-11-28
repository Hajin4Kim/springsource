//TODO: 화면에 업로드한 이미지 삭제 icon 누르면 삭제 요청 => 부모 이벤트
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  // a 태그 기능 중지
  e.preventDefault();
  // icon 눌러진 태그 요소 찾기
  console.log("e.target(실제 이벤트 대상) : ", e.target);
  console.log("e.currentTarget(이벤트 일어난 대상의 부모) : ", e.currentTarget);

  // href 값 가져오기
  const element = e.target.closest("a"); //TODO: target 대상과 가장 가까운 부모("") 를 찾아줘
  console.log(element);
  console.log(element.href); //TODO: .href 로 찍으면 프로토콜부터 다 갖고나옴
  console.log(element.getAttribute("href")); //TODO: 이렇게 해야 protocol 이후 부분 가져나옴

  //TODO: 화면 업로드된 썸네일삭제
  const removeLi = e.target.closest("li");

  //TODO: 삭제할 이미지 경로 추출 => 서버에 보내기
  const filePath = element.getAttribute("href");

  let formData = new FormData();
  formData.append("filePath", filePath);

  fetch("/upload/remove", {
    method: "post",
    body: formData,
  })
    .then((response) => {
      if (!response.ok) throw new Error("에러 발생");

      return response.text();
    })
    .then((data) => {
      //TODO: 삭제로직 진행 -> 화면 이미지까지 같이 제거
      if (data) removeLi.remove(); //TODO: html 태그 제거
    });
});
