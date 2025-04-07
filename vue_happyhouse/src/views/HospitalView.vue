<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="bookmark-heart"></b-icon> 병원정보
    </h3>
    <b-row>
      <b-col>
        <hospital-search-bar></hospital-search-bar>
      </b-col>
    </b-row>

    <b-row>
      <b-col cols="4" align="left">
        <hospital-kakao-map />
      </b-col>
      <b-col cols="4">
        <hospital-list />
      </b-col>
      <b-col cols="4">
        <hospital-detail />
      </b-col>
    </b-row>
    <!-- <router-view></router-view> -->
  </b-container>
</template>
<script>
import HospitalSearchBar from "@/components/hospital/HospitalSearchBar.vue";
import HospitalList from "@/components/hospital/HospitalList.vue";
import HospitalDetail from "@/components/hospital/HospitalDetail.vue";
import HospitalKakaoMap from "@/components/hospital/HospitalKakaoMap.vue";

export default {
  name: "HospitalView",
  components: {
    HospitalSearchBar,
    HospitalKakaoMap,
    HospitalList,
    HospitalDetail,
  },
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
