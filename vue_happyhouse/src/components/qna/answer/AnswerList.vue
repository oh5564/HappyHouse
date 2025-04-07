<template>
  <b-container>
    <!-- <b-row class="mt-3"> -->
    <answer-list-item
      v-for="answer in answers"
      :key="answer.ansno"
      v-bind="answer"
      :qnawriter="qna" />
    <!-- </b-row> -->
  </b-container>
</template>

<script>
//import { mapState } from "vuex";
import http from "@/api/http";
import AnswerListItem from "@/components/qna/answer/AnswerListItem";

//const qnaStore = "qnaStore";

export default {
  name: "AnswerList",
  components: {
    AnswerListItem,
  },
  data() {
    return {
      answers: [],
    };
  },
  props: ["qna"],
  created() {
    setTimeout(() => {
      //props are rendering after created
      //1. 질문 번호 받아오기
      const qnano = this.qna.articleno;
      console.log("qnano: ", qnano);
      //2. 리스트 받아오기
      http.get(`/answer/${qnano}`).then(({ data }) => {
        this.answers = data;
      });
      this.getQnano;
    });
  },
  // computed: {
  //   ...mapState(qnaStore, ["answers"]),
  // },
};
</script>

<style></style>
