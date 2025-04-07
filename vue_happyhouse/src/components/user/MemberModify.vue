<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">회원 정보 수정</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left; color: white">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group
            id="userid-group"
            label="아이디:"
            label-for="userid"
            description="아이디를 입력하세요.">
            <b-form-input
              id="userid"
              :disabled="isUserid"
              v-model="userInfo.userid"
              type="text"
              required
              ref="userid"
              placeholder="아이디 입력..."></b-form-input>
          </b-form-group>

          <b-form-group
            id="username-group"
            label="이름:"
            label-for="username"
            description="이름을 입력하세요.">
            <b-form-input
              id="username"
              v-model="userInfo.username"
              type="text"
              required
              ref="username"
              placeholder="이름 입력..."></b-form-input>
          </b-form-group>

          <b-form-group
            id="userpwd-group"
            label="비밀번호:"
            label-for="userpwd">
            <b-form-input
              id="userpwd"
              v-model="user.userpwd"
              type="text"
              required
              ref="userpwd"
              placeholder="비밀번호 입력..."></b-form-input>
          </b-form-group>

          <b-form-group id="email-group" label="이메일:" label-for="email">
            <b-form-input
              id="email"
              v-model="user.email"
              type="text"
              required
              ref="email"
              placeholder="이메일 입력..."></b-form-input>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1"
            >수정하기</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberModify",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        joindate: "",
      },
      isUserid: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  // props: {
  //   type: { type: String },
  // },
  created() {
    this.onCreated();
    // http.get(`/user/modify/${this.$route.params.userid}`).then(({ data }) => {
    //   // this.article.articleno = data.article.articleno;
    //   // this.article.userid = data.article.userid;
    //   // this.article.subject = data.article.subject;
    //   // this.article.content = data.article.content;
    //   this.userInfo = data;
    // });
    // this.userInfo = data;
    // this.isUserid = true;
  },
  methods: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
    ...mapActions(memberStore, ["getUserInfo"]),
    async onCreated() {
      let token = sessionStorage.getItem("access-token");
      console.log("token: ", token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.user = this.userInfo;
        this.isUserid = true;
      } else {
        alert("로그인 해주세요");
      }
    },

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      // !this.user.userid &&
      //   ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.userid.focus());
      // err &&
      //   !this.user.username &&
      //   ((msg = "이름을 입력해주세요"), (err = false), this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      if (!err) alert(msg);
      this.modifyUser();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.username = "";
      this.user.userpwd = "";
      this.user.email = "";
      this.$router.push({ name: "memberModify" }); //회원가입 완료 페이지
    },
    modifyUser() {
      http
        .put(`/user/modify`, {
          email: this.user.email,
          joindate: "",
          userid: this.user.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
        })
        .then(({ data }) => {
          console.log("data: ", data);
          let msg = "수정 시 문제가 발생했습니다.";
          if (data !== null) {
            msg = "수정이 완료되었습니다.";
          }
          // if (data === "success") {
          //   msg = "등록이 완료되었습니다.";
          // }
          alert(msg);
          this.moveHome();
        });
    },
    moveDetail() {
      this.$router.push({ name: "memberRegisterResult" });
      //this.$router.push({ name: "memberList" });
    },
    moveHome() {
      this.$router.push({ name: "signIn" }); //로그인 화면으로 이동
    },
  },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}
</style>
