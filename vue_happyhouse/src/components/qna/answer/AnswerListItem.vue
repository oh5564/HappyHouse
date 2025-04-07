<template>
  <!-- <b-col> -->
  <b-row class="mt-3">
    <!-- <b-col cols="11" class="bg-light p-2 pl-5 text-left">
        <span :class="{ completed: answer.completed }" @click="updateAnswerStatus">{{
          answer.title
        }}</span>
      </b-col>
      <b-col class="p-2"><b-button variant="danger" @click="deleteAnswer">X</b-button></b-col> -->
    <b-card class="w-100" title="" sub-title="">
      <b-card-text
        >작성자: {{ answriter }} &nbsp;&nbsp;&nbsp; 작성일:
        {{ regtime | dateFormat }} &nbsp;&nbsp;&nbsp; 추천 수:
        {{ recommendNum }}
      </b-card-text>
      <b-card-text>{{ anscontent }}</b-card-text>
      <hr id="hr-color" />
      <b-card-text> 도움이 되셨습니까?</b-card-text>

      <!-- <a href="#" class="card-link">Card link</a>
        <b-link href="#" class="card-link">Another link</b-link> -->
      <b-row>
        <b-col></b-col><b-col></b-col><b-col></b-col>
        <b-col
          class="p-2"
          v-if="questionWriter == getId"
          @click="updateAnsStatus">
          <b-button variant="outline-success" v-if="selectCopy == false"
            >채택하기</b-button
          >
          <b-button variant="success" v-else-if="selectCopy == true"
            >채택완료</b-button
          >
        </b-col>
        <!--{{recommendNum}} -->
        <b-col class="p-2" @click="upvoteAns">
          <b-button variant="outline-secondary" v-if="recommendCopy == false"
            >추천</b-button
          >
          <b-button variant="secondary" v-if="recommendCopy == true"
            >추천완료</b-button
          ></b-col
        >
        <b-col></b-col><b-col></b-col><b-col></b-col>
      </b-row>
      <b-col
        class="p-2"
        v-if="
          answriter == userInfo.userid ||
          userInfo.userid == 'admin' ||
          userInfo.userid == qnawriter.userid
        "
        ><b-button variant="outline-danger" @click="deleteAns"
          >삭제</b-button
        ></b-col
      >
    </b-card>
  </b-row>
  <!-- </b-col> -->
</template>

<script>
//import { mapActions } from "vuex";
//const qnaStore = "qnaStore";
import http from "@/api/http";
import moment from "moment";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "AnswerListItem",
  data() {
    return {
      selectCopy: false,
      recommendCopy: false,
      recommendNum: 0,
      questionWriter: "",
      userId: "",
    };
  },
  created() {
    http.get(`/qna/${this.qnano}`).then(({ data }) => {
      this.questionWriter = data.userid;
      console.log("questionWriter: ", this.questionWriter);
    });

    //this.getUserId();

    http.get(`/answer/detail/${this.ansno}`).then(({ data }) => {
      // console.log("data: ", data);
      // let msg = "정보를 가져오는데 문제가 발생했습니다.";
      // if (data !== "null") {
      //   msg = "정보를 가져오는데 성공했습니다.";
      //   console.log(msg);
      // } else {
      //   console.log(msg);
      // }
      this.selectCopy = data.isselected;
      this.recommendCopy = data.isrecommended;
      this.recommendNum = data.upvote;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    getId: function () {
      this.getUserId();
      return this.userId;
    },
  },
  props: {
    qnano: Number,
    ansno: Number,
    answriter: String,
    anscontent: String,
    isselected: Boolean,
    isrecommended: Boolean,
    upvote: Number,
    regtime: String,
    qnawriter: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
  methods: {
    getUserId() {
      //현재 로그인되어있는 유저 아이디
      this.userId = this.userInfo.userid;
    },
    updateAnsStatus() {
      //채택여부
      console.log("updateAnswer Call");
      //**질문자가 채택해야하므로 질문자 아이디로 로그인했을때만 채택 메뉴 보이게
      // 채택여부 가져오기
      http.get(`/answer/detail/${this.ansno}`).then(({ data }) => {
        // console.log("data: ", data);
        // let msg = "정보를 가져오는데 문제가 발생했습니다.";
        // if (data !== "null") {
        //   msg = "정보를 가져오는데 성공했습니다.";
        //   console.log(msg);
        // } else {
        //   console.log(msg);
        // }
        let isSelect = data.isselected;
        let isRecomm = data.isrecommended;

        //채택 여부 갱신
        http
          .put(`/answer/${this.ansno}`, {
            anscontent: this.anscontent,
            ansno: this.ansno,
            answriter: this.answriter,
            isrecommended: isRecomm,
            isselected: !isSelect,
            qnano: this.qnano,
            regtime: this.regtime,
            upvote: data.upvote,
          })
          .then(({ data }) => {
            //let msg = "채택 여부 갱신 시 문제가 발생했습니다.";
            if (data === "success") {
              //msg = "채택 여부 갱신이 완료되었습니다.";
              //console.log(msg);
            } else {
              //console.log(msg);
            }

            this.selectCopy = !isSelect;
            console.log("selectCopy:", this.selectCopy);
          });
        //this.moveList();
      });
    },
    upvoteAns() {
      //console.log("upvoteAns Call");

      //추천 수 가져오기
      http.get(`/answer/detail/${this.ansno}`).then(({ data }) => {
        console.log("data: ", data);
        //let msg = "정보를 가져오는데 문제가 발생했습니다.";
        // if (data !== "null") {
        //   msg = "정보를 가져오는데 성공했습니다.";
        //   //console.log(msg);
        // } else {
        //  // console.log(msg);
        // }

        let upvoteNum = 0;
        let isrecomm = !data.isrecommended;
        //console.log("data.isrecommended: ", data.isrecommended);
        if (data.isrecommended == false) {
          console.log("upvote");
          upvoteNum = data.upvote + 1;
        } else {
          console.log("downvote");
          upvoteNum = data.upvote - 1;
        }

        //추천 수 갱신
        http
          .put(`/answer/${this.ansno}`, {
            anscontent: this.anscontent,
            ansno: this.ansno,
            answriter: this.answriter,
            isrecommended: isrecomm,
            isselected: data.isselected,
            qnano: this.qnano,
            regtime: this.regtime,
            upvote: upvoteNum,
          })
          .then(({ data }) => {
            //let msg = "추천 수 갱신 시 문제가 발생했습니다.";
            if (data === "success") {
              //msg = "추천 수 갱신이 완료되었습니다.";
              //console.log(msg);
            } else {
              //console.log(msg);
            }

            this.recommendNum = upvoteNum;
            console.log("recommendNum:", this.recommendNum);

            this.recommendCopy = isrecomm;
            console.log("recommendCopy:", this.recommendCopy);
          });
      });
    },
    deleteAns() {
      console.log("deleteAnswer Call");

      http.delete(`/answer/${this.ansno}`).then(({ data }) => {
        let msg = "답변 삭제 시 문제가 발생했습니다.";
        console.log("data: ", data);
        if (data === "success") {
          msg = "답변 삭제가 완료되었습니다.";
          console.log(msg);
          //this.$router.push({ name: "qnaDetail" , articleno: this.qnano});
          this.$router.go();
          //this.$router.push({ name: "answerList" });
        } else {
          console.log(msg);
        }
      });
    },

    //mapActions 쓰면됨..!
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
  font-style: italic;
  font-weight: bold;
}

#hr-color {
  color: black;
}
</style>
