// 삭제 클릭 시 actionForm 전송
// 정말로 삭제하시겠습니까? 메세지 전송 후 ok 면 삭제 진행
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  // e.preventDefault();
  if (confirm("정말로 삭제하시겠습니까?")) {
    actionForm.action = "/book/remove";
    actionForm.submit();
  }
});

// 목록 클릭 시, a 태그 기능 중지
document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  e.preventDefault();
  // actionForm 에서 id 요소 제거
  actionForm.querySelector("[name='id']").remove();

  // actionForm method를 get 으로 변경
  actionForm.method = "get";

  // actionForm action은 list 변경하기
  actionForm.action = "/book/list";

  // actionForm submit()
  actionForm.submit();
});
