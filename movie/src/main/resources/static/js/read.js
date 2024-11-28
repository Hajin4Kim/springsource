//TODO: 영화의 전체 리뷰 가져오기
const reviewLoaded = () => {
  fetch(`/reviews/${mno}/all`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
    });
};

reviewLoaded();

//TODO: 이미지 모달 요소 가져오기
const imgModal = document.querySelector("#imgModal");

if (imgModal) {
  imgModal.addEventListener("shown.bs.modal", (e) => {
    //TODO: "show.bs.modal"
    // 모달을 뜨게 만든 img 요소 가져오기
    const posterImg = e.relatedTarget;

    // data- 가져오기
    const file = posterImg.getAttribute("data-file");
    console.log(file);

    // 선택한 사진의 영화명 가져와서 모달에 title 에도 띄우기
    imgModal.querySelector(".modal-title").textContent = `${title}`;

    imgModal.querySelector(
      ".modal-body"
      //TODO: &size=1은 큰 사진 보여주기값 -> 그렇지 않으면 s_ 붙은 썸네일사진 가져와서 해상도 떨어짐
    ).innerHTML = `<img src="/upload/display?fileName=${file}&size=1" alt="" style="width:100%">`;
  });
}
