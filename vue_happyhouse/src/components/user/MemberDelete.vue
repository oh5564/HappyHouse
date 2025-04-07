<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3 id="text1">회원 정보</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col id="text2">탈퇴 중...</b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
//import http from "";

export default {
  name: "MemberDelete",
  created() {
    http
      .delete(`/user/delete/${this.$route.params.memberid}`)
      .then(({ data }) => {
        console.log("data: ", data);
        let msg = "탈퇴 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "회원 탈퇴가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "home" });
      });
  },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}

#text2 {
  color: #748ca4;
}
</style>
