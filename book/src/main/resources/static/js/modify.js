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
