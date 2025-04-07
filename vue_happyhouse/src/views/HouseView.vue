<template>
  <b-container class="bv-example-row mt-5 text-center">
    <h3 id="text1">아파트 매매 정보</h3>
    <div style="margin-top: 30px">
      <div
        style="
          position: absolute;
          left: 0;
          z-index: 8;
          width: 25%;
          height: 15%;
          border-radius: 5px;
          margin-top: 10px;
          margin-bottom: 10px;
          margin-left: 10px;
          margin-right: 10px;
        ">
        <house-search-bar></house-search-bar>
      </div>
      <b-row>
        <div
          style="
            position: absolute;
            left: 0;
            width: 55%;
            height: 80%;
            margin-left: 10px;
          ">
          <kakao-map />
        </div>
        <b-col
          style="
            position: absolute;
            right: 23%;
            width: 20%;
            padding: 20px;
            box-shadow: 0 0 10px #666;
            color: whitesmoke;
            border-radius: 5px;
            margin-left: 10px;
            background-color: white;
          ">
          <HouseList />
        </b-col>
        <b-col
          style="
            margin-left: 800px;
            position: absolute;
            right: 0;
            width: 20%;
            box-shadow: 0 0 10px #666;
            color: whitesmoke;
            border-radius: 5px;
            margin-right: 20px;
            background-color: white;
            overflow: auto;
          ">
          <HouseDetail />
        </b-col>
      </b-row>
      <b-row>
        <b-col
          style="
            position: absolute;
            right: 23%;
            width: 20%;
            top: 70%;
            padding: 20px;
            box-shadow: 0 0 10px #666;
            color: whitesmoke;
            border-radius: 5px;
            margin-left: 10px;
            background-color: white;
          ">
          <HospitalList />
        </b-col>
        <b-col
          style="
            position: absolute;
            right: 0%;
            width: 20%;
            top: 85%;
            box-shadow: 0 0 10px #666;
            color: whitesmoke;
            border-radius: 5px;
            margin-right: 20px;
            margin-top: 30px;
            background-color: white;
          ">
          <HospitalDetail />
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
//import HouseAvgListTemp from "@/components/houseTemp/HouseAvgList2.vue";
//import HouseAvgListSinCityTemp from "@/components/houseTemp/HouseAvgListSinCity2.vue";
import KakaoMap from "@/components/house/KakaoMap.vue";
import HospitalList from "@/components/hospital/HospitalList.vue";
import HospitalDetail from "@/components/hospital/HospitalDetail.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    KakaoMap,
    HouseList,
    HouseDetail,
    HospitalList,
    HospitalDetail,
  },
};
</script>
<style>
#text1 {
  /* display: inline-block; */
  /* background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  ); */
  font-family: NanumSquareRoundB, sans-serif;
  color: white;
}
</style>
