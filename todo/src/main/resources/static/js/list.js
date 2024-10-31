//TODO: 선생님 답
document.querySelector("tbody").addEventListener("click", (e) => {
  // "완료" 를 클릭하면 checkbox 의 value 가져오기
  const chk = e.target;
  console.log(chk.value);
  console.log(chk.checked);
  // comForm 에 id value 값을 가져온 id로 변경
  const comForm = document.querySelector("#comForm");
  comForm.querySelector("[name='id']").value = chk.value;
  comForm.querySelector("[name='completed']").value = chk.checked;

  console.log(comForm.innerHTML);
  comForm.submit();
});

// //TODO: 내 답
// const completedCheckboxes = document.querySelectorAll(
//   'input[type="checkbox"][name="completed"]'
// );
// completedCheckboxes.forEach((checkbox) => {
//   checkbox.addEventListener("change", function () {
//     // Check if the checkbox is being ticked
//     if (this.checked) {
//       // Get the task ID from the checkbox value
//       const taskId = this.value;
//       // Set the hidden input value in the form
//       const comForm = document.getElementById("comForm");
//       const hiddenInput = comForm.querySelector('input[name="id"]');
//       hiddenInput.value = taskId;
//       console.log(taskId);
//     }
//   });
// });
