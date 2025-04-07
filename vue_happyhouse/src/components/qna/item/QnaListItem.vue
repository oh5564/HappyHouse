<template>
  <b-tr class="qna-list">
    <b-td
      ><span id="text-td">{{ articleno }}</span></b-td
    >
    <b-th class="text-left" id="text1">
      <router-link
        :to="{ name: 'qnaDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td
      ><span id="text-td">{{ userid }}</span></b-td
    >
    <b-td
      ><span id="text-td">{{ regtime | dateFormat }}</span></b-td
    >
    <!--답변 여부-->
    <b-td>
      <!-- <span @click="change">{{ answer | shape }}</span> -->
      <span id="text-td"> {{ answerCount }} </span>
    </b-td>
  </b-tr>
</template>

<script>
import http from "@/api/http";
import moment from "moment";
//import { mapGetters } from "vuex";
//const qnaStore = "qnaStore";

export default {
  data() {
    return {
      answer: false,
      answerCount: 0,
    };
  },
  methods: {
    change() {
      this.answer = !this.answer;
    },
  },
  name: "QnaListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    content: String,
    regtime: String,
  },
  filters: {
    shape(data) {
      if (data === true) return "O";
      else return "X";
    },
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  mounted() {
    // ...mapGetters(qnaStore, [
    //   "allAnswersCount",
    //   // "completedTodosCount",
    //   // "unCompletedTodosCount",
    // ]),
    // console.log(this.articleno);
    http.get(`/answer/${this.articleno}`).then(({ data }) => {
      //console.log("data: ", data);
      //let msg = "데이터 반환에 문제가 발생했습니다.";
      if (data !== null) {
        //msg = "데이터 반환이 완료되었습니다.";
      }
      //console.log(msg);
      // 현재 route를 /list로 변경.
      //console.log(data.length);
      this.answerCount = data.length;
      //console.log(this.answerCount);
    });
  },
};
</script>

<style>
#text-td {
  color: #07305d;
}

.qna-list {
  background-color: whitesmoke;
}
</style>
