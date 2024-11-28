const fileInput = document.querySelector("[type = 'file']");

//TODO: 업로드 한 이미지들 화면에 띄우기
function showUploadImages(files) {
  // 이미지 보여줄 영역 찾아오기 ( class = uploadResult )
  const uploadResult = document.querySelector(".uploadResult ul");

  let tags = "";
  files.forEach((file) => {
    tags += `<li data-name="${file.fileName}" data-path="${file.folderPath}" data-uuid="${file.uuid}"><div>`;
    tags += `<div>`;
    tags += `<a href=""><img src="/upload/display?fileName=${file.thumbImageURL}" class="block" /></a>`;
    tags += `<span class="text-sm d-inline-block mx-1">${file.fileName}</span>`;
    tags += `<a href="${file.imageURL}" data-file=""><i class="fa-solid fa-delete-left"></i></a>`;
    tags += `</div>`;
    tags += `</li>`;
  });

  uploadResult.insertAdjacentHTML("beforeend", tags);
}

//TODO: [type = file] 에 change 이벤트 처리 (=상태가 change 되는 순간 썸네일 업로드)
fileInput.addEventListener("change", (e) => {
  const files = e.target.files;

  //TODO: 스크립트에서 form 작성
  let formData = new FormData();
  for (let index = 0; index < files.length; index++) {
    formData.append("uploadFiles", files[index]);
  }

  //TODO: formData 서버로 전송(ajax)
  fetch("/upload/upload", {
    method: "post",
    body: formData,
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("change 이벤트 성공 & data :", data);

      //TODO: 업로드된 첨부 파일 화면에 출력하는 함수
      showUploadImages(data);
    });
});

// TODO: 등록 버튼 클릭 시, (create 등록 동작)
const createForm = document.querySelector("#createForm");
createForm.addEventListener("submit", (e) => {
  //TODO:작성버튼 클릭 시, form submit 중지
  e.preventDefault();

  // 첨부파일 정보 수집 : uploadResult li
  // data-name="" data-path="" data-uuid=""
  // 태그 (요소.dataset.name / 요소.dataset.path  /요소.dataset.uuid)

  const attachInfos = document.querySelectorAll(".uploadResult li");
  let result = "";

  attachInfos.forEach((obj, idx) => {
    // console.log("idx = ", idx);

    // console.log(obj.dataset.name);
    // console.log(obj.dataset.path);
    // console.log(obj.dataset.uuid);
    result += `<input type="hidden" name="movieImageDtos[${idx}].path" value="${obj.dataset.path}">`;
    result += `<input type="hidden" name="movieImageDtos[${idx}].uuid" value="${obj.dataset.uuid}">`;
    result += `<input type="hidden" name="movieImageDtos[${idx}].imgName" value="${obj.dataset.name}">`;
  });
  e.target.insertAdjacentHTML("beforeend", result);
  //TODO: 폼 내용 확인 (append 확인)
  console.log(e.target.innerHTML);

  //TODO: .querySelector 하나 일 때,
  // console.log(attachInfos.dataset.name);
  // console.log(attachInfos.dataset.path);
  // console.log(attachInfos.dataset.uuid);

  e.target.submit();
});
