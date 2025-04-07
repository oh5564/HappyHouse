<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">회원가입</h3>
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
              placeholder="이름 입력..."></b-form-input>
          </b-form-group>

          <b-form-group
            id="userpwd-group"
            label="비밀번호:"
            label-for="userpwd">
            <b-form-input
              id="userpwd"
              v-model="userInfo.userpwd"
              type="text"
              required
              placeholder="비밀번호 입력..."></b-form-input>
          </b-form-group>

          <b-form-group id="email-group" label="이메일:" label-for="email">
            <b-form-input
              id="email"
              v-model="userInfo.email"
              type="text"
              required
              placeholder="이메일 입력..."></b-form-input>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1"
            >회원가입</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MemberRegister",
  data() {
    return {
      userInfo: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
      },
      isUserid: false,
    };
  },
  // props: {
  //   type: { type: String },
  // },
  created() {
    // if (this.type === "modify") {
    //   http.get(`/user/modify/${this.$route.params.articleno}`).then(({ data }) => {
    //     // this.article.articleno = data.article.articleno;
    //     // this.article.userid = data.article.userid;
    //     // this.article.subject = data.article.subject;
    //     // this.article.content = data.article.content;
    //     this.article = data;
    //   });
    //   this.isUserid = true;
    // }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.userInfo.userid &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.userInfo.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.userInfo.userpwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());
      err &&
        !this.userInfo.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      if (!err) alert(msg);
      this.registMember();
    },
    onReset(event) {
      event.preventDefault();
      this.userInfo.userid = 0;
      this.userInfo.username = "";
      this.userInfo.userpwd = "";
      this.userInfo.email = "";
      this.$router.push({ name: "memberRegister" }); //회원가입 완료 페이지
    },
    registMember() {
      http
        .post(`/user/register`, {
          email: this.userInfo.email,
          joindate: "",
          userid: this.userInfo.userid,
          username: this.userInfo.username,
          userpwd: this.userInfo.userpwd,
        })
        .then(({ data }) => {
          console.log("data: ", data);
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data !== null) {
            msg = "등록이 완료되었습니다.";
          }
          // if (data === "success") {
          //   msg = "등록이 완료되었습니다.";
          // }
          alert(msg);
          this.moveDetail();
        });
    },
    moveDetail() {
      this.$router.push({ name: "memberRegisterResult" });
      //this.$router.push({ name: "memberList" });
    },
  },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}
</style>
