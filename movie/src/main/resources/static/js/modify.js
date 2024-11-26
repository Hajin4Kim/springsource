//TODO: modifyForm 찾은 후 action ="/movie/delete" 변경
const modifyForm = document.querySelector("#modifyForm");
const deleteBtn = document.querySelector("#deleteBtn");

if (modifyForm) {
  modifyForm.addEventListener("submit", (e) => {
    e.preventDefault();

    if (!confirm("정말로 삭제하시겠습니까?")) {
      return;
    }
    modifyForm.action = "/movie/delete";
    modifyForm.submit();
  });
}

// // listBtn 클릭 시
// document.querySelector("#listBtn").addEventListener("submit", () => {
//   modifyForm.action = "/movie/list";
//   modifyForm.submit();
// });
