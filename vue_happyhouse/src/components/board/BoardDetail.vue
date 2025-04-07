<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">공지</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="secondary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="userInfo.userid == 'admin'">
        <b-button
          variant="info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${article.subject} </h3><div><h6>${article.userid}</div><div>[조회 수: ${article.hit}]</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      console.log("data: ", data);
      this.article = data;
      console.log("this.article: ", this.article);
      let hitup = data.hit + 1;

      http
        .put(`/board/${data.articleno}`, {
          articleno: data.articleno,
          content: data.content,
          hit: data.hit + 1,
          subject: data.subject,
          userid: data.userid,
        })
        .then(() => {
          // let msg = "조회수 수정 처리시 문제가 발생했습니다.";
          // if (data2 === "success") {
          //   msg = "조회수 수정이 완료되었습니다.";
          // }
          // console.log(msg);
          this.article.hit = hitup;
          // console.log("this.article.hit: ", this.article.hit);
        });
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boardDelete",
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
