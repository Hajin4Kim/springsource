document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  if (e.keyCode == 13) {
    // 검색어 입력 여부 확인
    const keyword = e.target.value;
    if (!keyword) {
      // -> 없으면 메세지 + 돌려보내기
      alert("검색어를 입력 후, 찾기 버튼 실행하시오");
      return;
    }

    // 있으면 keyword 가져온 후,
    // searchForm 찾아 name="keyword" 부분의 입력값 변경
    const searchForm = document.querySelector("#searchForm");
    searchForm.querySelector("[name='keyword']").value = keyword;

    //searchForm submit
    searchForm.submit();
  }
});
