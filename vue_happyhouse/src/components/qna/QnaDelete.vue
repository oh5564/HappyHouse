<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">문의 목록</h3>
      </b-col>
    </b-row>
    <b-row class="mt-5">
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "QnaDelete",
  created() {
    //1. 답변들 삭제 하고
    http
      .delete(`/answer/deleteByAnsno/${this.$route.params.articleno}`)
      .then(({ data }) => {
        //let msg = "질문에 대한 답변 삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          // msg = "삭제가 완료되었습니다.";
        }
        //alert(msg);

        //2. 질문 삭제
        http.delete(`/qna/${this.$route.params.articleno}`).then(({ data }) => {
          let msg = "질문 삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "qnaList" });
        });
      });
  },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}
</style>
