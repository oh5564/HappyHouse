<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">문의 상세</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="secondary" size="sm" @click="listArticle"
          >목록</b-button
        >
      </b-col>
      <b-col
        class="text-right"
        v-if="article.userid == userInfo.userid || userInfo.userid == 'admin'">
        <b-button
          variant="info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >수정</b-button
        >
        <b-button variant="danger" size="sm" @click="deleteArticle"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${article.subject} </h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>

    <!--답글 -->
    <b-row class="mb-1">
      <b-col>
        <answer-input v-bind:qna="article"></answer-input>
        <!-- <router-link :to="{name: 'answerInput', query: { articleno: article.articleno }}"></router-link> -->
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <answer-list v-bind:qna="article"></answer-list>
        <!-- <router-link :to="{name: 'answerList', query: { articleno: article.articleno }}"></router-link> -->
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import AnswerInput from "@/components/qna/answer/AnswerInput.vue";
import AnswerList from "@/components/qna/answer/AnswerList.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "QnaDetail",
  data() {
    return {
      article: {},
    };
  },
  components: {
    AnswerInput,
    AnswerList,
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(memberStore, ["userInfo"]), // 로그인한 유저정보
  },
  created() {
    http.get(`/qna/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "qnaList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "qnaModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "qnaDelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}
</style>
