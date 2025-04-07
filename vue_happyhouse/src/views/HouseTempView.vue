<template>
  <b-container class="bv-example-row mt-5 text-center">
    <h3 id="text1">아파트 매매 정보</h3>
    <b-row>
      <b-col>
        <house-search-bar-temp></house-search-bar-temp>
      </b-col>
    </b-row>
    <b-row class="mt-4 mb-4 justify-content-left">
      <b-col class="mt-5" cols="6">
        <div class="ml-3">
          <!-- 맵 & 리스트 : 왼쪽 배치-->
          <kakao-map-temp />
        </div>
        <div>
          <house-list-temp />
        </div>
      </b-col>
      <b-col cols="6">
        <!-- 상세 :  오른쪽 배치 -->
        <house-detail-temp />
      </b-col>
    </b-row>
    <b-row>
      <!-- 병원 리스트 : 왼쪽 배치-->
      <b-col class="mt-5" cols="6">
        <hospital-list />
      </b-col>
      <!-- 병원 상세 : 오른쪽 배치-->
      <b-col class="mt-5" cols="6">
        <hospital-detail />
      </b-col>
    </b-row>
    <!-- <b-row>
      <b-col cols="6">
        <house-avg-list-temp />
      </b-col>
      <b-col cols="6">
        <house-avg-list-sin-city-temp />
      </b-col>
    </b-row> -->
  </b-container>
</template>
<script>
import HouseSearchBarTemp from "@/components/house/HouseSearchBar.vue";
import HouseListTemp from "@/components/house/HouseList.vue";
import HouseDetailTemp from "@/components/house/HouseDetail.vue";
//import HouseAvgListTemp from "@/components/houseTemp/HouseAvgList2.vue";
//import HouseAvgListSinCityTemp from "@/components/houseTemp/HouseAvgListSinCity2.vue";
import KakaoMapTemp from "@/components/house/KakaoMap.vue";
import HospitalList from "@/components/hospital/HospitalList.vue";
import HospitalDetail from "@/components/hospital/HospitalDetail.vue";

export default {
  name: "HouseTempView",
  components: {
    HouseSearchBarTemp,
    KakaoMapTemp,
    HouseListTemp,
    HouseDetailTemp,
    HospitalList,
    HospitalDetail,
    //HouseAvgListTemp,
    //HouseAvgListSinCityTemp,
  },
};
</script>
<style scoped>
#text1 {
  display: inline-block;
  /* background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  ); */
  font-family: NanumSquareRoundB, sans-serif;
  color: whitesmoke;
}
</style>
