<template>
  <b-container class="bv-example-row">
    <b-row class="text-center">
      <b-col>
        <b-input-group prepend="답변" class="mt-3">
          <b-form-input
            v-model.trim="answerTitle"
            @keypress.enter="createAns"></b-form-input>
          <b-input-group-append>
            <b-button variant="secondary" @click="registerAns">등록</b-button>
            <!--@click="createAns"-->
          </b-input-group-append>
        </b-input-group>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";

const qnaStore = "qnaStore";
const memberStore = "memberStore";

export default {
  name: "AnswerInput",
  data() {
    return {
      answerTitle: "",
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        joindate: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: ["qna"],
  methods: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
    ...mapActions(qnaStore, ["createAnswer"]),
    ...mapActions(memberStore, ["getUserInfo"]),

    createAns() {
      console.log("answer: ", this.$store);
      const answerItem = {
        title: this.answerTitle,
        completed: false,
      };
      console.log("answerItem.title: ", answerItem.title);
      if (answerItem.title) {
        console.log("create");
        //this.$store.commit('CREATE_ANSWER', answerItem);
        //this.$store.dispatch("createAnswer", answerItem);
        this.createAnswer(answerItem);
      }
      this.answerTitle = "";
    },

    async registerAns() {
      //1. 질문번호 받아오고
      const qnano = this.qna.articleno;
      this.user = this.userInfo;
      console.log("qnano: ", qnano);

      //2. 로그인한 유저 아이디 받아오고
      let token = sessionStorage.getItem("access-token");

      if (this.isLogin) {
        await this.getUserInfo(token);
        this.user = this.userInfo;
        //console.log("userid: ", this.user.userid);
      } else {
        alert("로그인 해주세요");
      }

      //3. 답변 등록
      http
        .post(`/answer/write`, {
          // userid: this.article.userid,
          // subject: this.article.subject,
          // content: this.article.content,
          anscontent: this.answerTitle,
          answriter: this.user.userid, //현재 로그인해있는 아이디,
          qnano: qnano,
          isselected: "false",
          isrecommended: "false",
          upvote: 0,
        })
        .then(({ data }) => {
          let msg = "답변 등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "답변 등록이 완료되었습니다.";
            console.log(msg);
          } else {
            alert(msg);
          }

          this.$router.go();
          //this.$router.push({ name: "answerInput" });
        });
    },
  },
};
</script>

<style></style>
