// 전체 댓글 내용 표시
const replyList = document.querySelector(".reply-list");

// 날짜 처리 함수
const formatDateTime = (str) => {
  const date = new Date(str);
  return (
    date.getFullYear() +
    "/" +
    (date.getMonth() + 1) +
    "/" +
    date.getDate() +
    " " +
    date.getHours() +
    ":" +
    date.getMinutes()
  );
};

// 페이지가 로드되면 현재 bno의 댓글들을 자동으로 가져오기 (postman 에서 테스트한 것을 화면단에서)

const replyLoaded = () => {
  fetch(`/replies/board/${bno}`)
    .then((response) => {
      if (!response.ok) throw new Error("replyLoaded 에러 발생");

      return response.json();
    })
    .then((data) => {
      console.log(data);

      // 전체 댓글 수 표시
      document.querySelector(".d-inline-block").innerHTML = data.length;

      let result = "";
      data.forEach((reply) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom reply-row" data-rno="${reply.rno}">`;
        result += `<div class="p-3"><img src="/img/default.png" alt="" class="rounded-circle mx-auto d-block" style="width: 60px; height: 60px" /></div>`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<span>${reply.replyer}</span>`;
        result += `<div><span class="fs-5">${reply.text}</span></div>`;
        result += `<div class="text-muted"><span class="small">${formatDateTime(
          reply.regDate
        )}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center"><div class="mb-2">`;
        result += `<button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
        result += `<div><button class="btn btn-outline-success btn-sm">수정</button></div>`;
        result += `</div></div>`;
      });
      replyList.innerHTML = result;
    });
};
replyLoaded();

//TODO: 댓글 작성 폼
const replyForm = document.querySelector("#replyForm");
replyForm.addEventListener("submit", (e) => {
  e.preventDefault();
  // replyForm 안에 있는 replyer, text 의 value 가져와서 변수에 담기
  const replyer = replyForm.querySelector("#replyer");
  const text = replyForm.querySelector("#text");
  //TODO: 댓글 수정 시, 사용할 rno
  const rno = replyForm.querySelector("#rno");

  // 자바 스크립트 객체 생성
  const reply = {
    text: text.value,
    replyer: replyer.value,
    bno: bno,
    rno: rno.value,
  };

  if (!rno.value) {
    //TODO: 새 댓글
    fetch(`/replies/new`, {
      headers: {
        "content-type": "application/json",
      },
      //TODO: JSON.stringify(객체) => json 형태로 변환
      body: JSON.stringify(reply),
      method: "post",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("댓글 삽입 에러발생");
        }
        return response.text();
      })
      .then((data) => {
        console.log(data);
        if (data) {
          // 댓글 폼에 남아있는 내용 제거
          replyer.value = "";
          text.value = "";
          // data번 댓글이 등록되었습니다(alert창)
          alert(data + " 번 댓글이 등록되었습니다.");
          // 창 새로고침
          replyLoaded();
        }
      });
  } else {
    //TODO: 수정 댓글
    fetch(`/replies/${rno.value}`, {
      headers: {
        "content-type": "application/json",
      },
      //TODO: JSON.stringify(객체) => json 형태로 변환
      body: JSON.stringify(reply),
      method: "put",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("댓글 수정 에러발생");
        }
        return response.text(); //TODO: 도착한 data의 형태에 따라(우리의 경우 Long 으로 오므로)/Dto로 오는 경우는 .json()으로
      })
      .then((data) => {
        console.log(data);
        if (data) {
          // 댓글 폼에 남아있는 내용 제거
          replyer.value = "";
          text.value = "";
          rno.value = "";
          alert(data + " 번 댓글이 수정되었습니다.");
          // 창 새로고침
          replyLoaded();
        }
      });
  }
});

//TODO: 댓글 수정
// 수정 버튼 클릭되면 해당 댓글(text)이 replyForm 안에 보여지도록 (rno 필요)

// 이벤트 전파를 이용해 이벤트 감지(부모 클릭 감지시,)
replyList.addEventListener("click", (e) => {
  // 실제 이벤트 발생 요소는 누구인가?
  console.log(e.target);

  // 이벤트 가 발생한 버튼이 속한 data-rno를 가지고 있는 부모 태크 찾아오기
  const btn = e.target;
  // console.log(btn.closest(".reply-row"));
  const rno = btn.closest(".reply-row").dataset.rno;

  // 수정 버튼 클릭 시 rno 가져오기 (data-rno)
  // 수정버튼인지 삭제 버튼인지 확인하기
  // 클래스명 : classList
  if (btn.classList.contains("btn-outline-danger")) {
    //TODO: 댓글 삭제
    fetch(`/replies/${rno}`, {
      method: "delete",
    })
      .then((response) => {
        console.log("rno" + rno);
        return response.text();
      })
      .then((data) => {
        console.log(data);
        alert(data + " 번 댓글이 삭제되었습니다.");

        // 댓글 영역 다시 가져오기 호출
        replyLoaded();
      });
  } else if (btn.classList.contains("btn-outline-success")) {
    //TODO: 댓글 수정
    fetch(`/replies/${rno}`)
      .then((response) => {
        console.log("rno" + rno);
        return response.json();
      })
      .then((data) => {
        console.log(data);
        // 해당 댓글을 replyForm 안에 보여주기
        //TODO: read.html에 form input:hidden 으로 rno 넣어놔야 함
        replyForm.querySelector("#rno").value = data.rno;
        replyForm.querySelector("#replyer").value = data.replyer;
        replyForm.querySelector("#text").value = data.text;
      });
  }
});
