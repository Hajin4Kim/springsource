//TODO: 포스터 추가 시, 삭제 기능
// 화면에 업로드한 이미지 삭제 icon 누르면 삭제 요청 => 부모 이벤트
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  if (e.target.tagName !== "I") return;

  // a 태그 기능 중지
  e.preventDefault();

  // href 값 가져오기
  const element = e.target.closest("li"); //TODO: target 대상과 가장 가까운 부모("") 를 찾아줘

  //TODO: 서버 저장한 포스터 삭제 XXX => 수정버튼 누르고 확인 해야 삭제
  if (confirm("정말로 이미지를 삭제하시겠습니까?")) {
    element.remove();
  }

  e.stopPropagation(); //TODO: 이벤트 전파 막는 코드
});

//TODO: actionForm 찾은 후 action ="/movie/delete" 변경
const form = document.querySelector("#actionForm");
const deleteBtn = document.querySelector("#createForm .bg-red-600");
const listBtn = document.querySelector("#createForm .bg-blue-600");

if (deleteBtn) {
  deleteBtn.addEventListener("click", () => {
    if (!confirm("정말로 삭제하시겠습니까?")) {
      return;
    }

    form.action = "/movie/delete";
    form.submit();
  });
}

listBtn.addEventListener("click", () => {
  form.querySelector("[name='mno']").remove();

  form.method = "get";
  form.action = "/movie/list";
  form.submit();
});
