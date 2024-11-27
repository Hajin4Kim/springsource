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
  // form submit 중지
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
