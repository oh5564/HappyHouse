<template>
  <div class="p-3 mb-2 bg-secondary text-white">
    <!-- 1. 병원 종합 정보 2. 약국 정보 3. 선별진료소 정보 선택 메뉴 -->
    <b-row class="mt-4 mb-4 m1-4 mr-4 text-center">
      <b-col class="sm-3">
        <div>검색 조건을 선택해주세요:</div>
        <b-form-select
          v-model="selected"
          :options="options"
          size="sm"
          class="w-50 mt-3 mr-4 ml-4"></b-form-select>
        <div class="mt-3">
          Selected: <strong>{{ selected }}</strong>
        </div>
      </b-col>
    </b-row>
    <!--일반 병원 검색 시-->
    <div v-if="selected == 'hospitalGeneral'">
      <b-row class="mt-4 mb-4 text-center">
        <b-col class="sm-3"> 일반 병원 검색 결과입니다. </b-col>
      </b-row>
      <b-row class="mt-4 mb-4 text-center">
        0. 전체리스트 반환(test)
        <input type="button" value="검색" @click="hospitallist" />
      </b-row>
      <b-row class="mt-4 mb-4 text-center">
        1. 동읍면으로 검색
        <b-col class="sm-3">
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            v-model="dongCode"
            :options="dongs"
            @change="searchHospitalByDong($event)"></b-form-select>
        </b-col>
      </b-row>
      <b-row class="mt-4 mb-4 text-center">
        <b-col>
          <label for="apt">2. 이름으로 검색:</label>
          <input
            type="text"
            v-model="hospital"
            id="hospital"
            @keydown.enter="searchHospitalByName" />
          <input type="button" value="검색" @click="searchHospitalByName" />
        </b-col>
      </b-row>
      <b-row class="w-50 mt-4 mb-4 text-center">
        <b-col>
          <label for="apt">3. 분류명으로 검색:</label>
          <b-form-select
            v-model="selectedSort"
            :options="optsSort"
            @change="searchHospitalBySort"></b-form-select>
          <div class="mt-3">
            Selected: <strong>{{ selectedSort }}</strong>
          </div>
        </b-col>
      </b-row>
    </div>
    <!--약국 정보 검색 시-->
    <b-row class="mt-4 mb-4 text-center" v-if="selected == 'pharmacy'">
      <b-col class="sm-3"> 약국 정보 검색 결과입니다. </b-col>
    </b-row>
    <!--선별진료소 검색 시-->
    <b-row class="mt-4 mb-4 text-center" v-if="selected == 'hospitalCovid'">
      <b-col class="sm-3"> 선별진료소 검색 결과입니다. </b-col>
    </b-row>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";
const hospitalStore = "hospitalStore";

export default {
  name: "HospitalSearchBar",
  data() {
    return {
      selected: null,
      options: [
        { text: "검색조건 선택", value: null },
        { text: "일반 병원 검색", value: "hospitalGeneral", disabled: false },
        { text: "약국 검색", value: "pharmacy", disabled: false },
        {
          text: "코로나 선별진료소 검색",
          value: "hospitalCovid",
          disabled: false,
        },
      ],
      selectedSort: null,
      optsSort: [
        { value: null, text: "분류 선택" },
        { value: "01", text: "내과" },
        { value: "02", text: "신경과" },
        { value: "03", text: "정신건상의학과" },
        { value: "04", text: "외과" },
        { value: "05", text: "정형외과" },
        { value: "06", text: "신경외과" },
        { value: "07", text: "흉부외과" },
        { value: "08", text: "성형외과" },
        { value: "09", text: "마취통증의학과" },
        { value: "10", text: "산부인과" },
        { value: "11", text: "소아청소년과" },
        { value: "12", text: "안과" },
        { value: "13", text: "이비인후과" },
        { value: "14", text: "피부과" },
        { value: "15", text: "비뇨의학과" },
        { value: "16", text: "영상의학과" },
        { value: "17", text: "방사선종양학과" },
        { value: "18", text: "병리과" },
        { value: "19", text: "진단검사의학과" },
        { value: "20", text: "결핵과" },
        { value: "21", text: "재활의학과" },
        { value: "24", text: "응급의학과" },
        { value: "50", text: "구강악안면외과" },
        { value: "51", text: "치과보철과" },
        { value: "52", text: "치과교정과" },
        { value: "53", text: "소아치과" },
        { value: "54", text: "치주과" },
        { value: "55", text: "치과보존과" },
        { value: "56", text: "구강내과" },
        { value: "59", text: "예방치과" },
        { value: "61", text: "통합치의학과" },
        { value: "80", text: "한방내과" },
      ],
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      dongName: "",
      hospital: "",
      min: 0,
      max: 0,
    };
  },
  computed: {
    ...mapState(hospitalStore, ["sidos", "guguns", "dongs", "hospitals"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(hospitalStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHospitalList",
      "getHospitalListbyDong",
      "getHospitalListByName",
      "getHospitalListBySort",
    ]),
    ...mapMutations(hospitalStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_SIDO",
      "SET_GUGUN",
      "SET_DONG",
    ]),
    gugunList() {
      //console.log(this.sidoCode);
      this.SET_SIDO(this.sidoCde);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.SET_GUGUN(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    hospitallist() {
      //0. 일반 병원 전체 리스트 반환(test)
      this.getHospitalList();
    },
    searchHospitalByDong(event) {
      //1. 일반 병원 동으로 조회
      //console.log("event: ", event);
      this.SET_DONG(this.dongCode); //코드 -> 이름으로 변경
      if (this.dongCode) {
        //console.log("this.dongCode: ", this.dongCode);
        //console.log("this.dongs: ", this.dongs); //여러게나옴..
        this.getHospitalListbyDong(event);
      }
    },
    searchHospitalByName() {
      //2. 일반 병원 이름으로 조회
      if (this.hospital) {
        if (this.dongCode) {
          this.getHospitalListByName({
            hospital: this.hospital,
            state: this.dongCode,
            sort: "dong",
          });
        } else if (this.gugunCode) {
          this.getHospitalListByName({
            hospital: this.hospital,
            state: this.gugunCode,
            sort: "gugun",
          });
        } else if (this.sidoCode) {
          this.getHospitalListByName({
            hospital: this.hospital,
            state: this.sidoCode,
            sort: "sido",
          });
        } else {
          this.getHospitalListByName({
            hospital: this.hospital,
            state: "null",
          });
        }
      }
    },
    searchHospitalBySort() {
      //2. 일반 병원 종류로 조회
      console.log("selectedSort: ", this.selectedSort);
      if (this.selectedSort) {
        if (this.dongCode) {
          this.getHospitalListBySort({
            hospital: this.hospital,
            state: this.dongCode,
            sort: "dong",
            select: this.selectedSort,
          });
        } else if (this.gugunCode) {
          this.getHospitalListBySort({
            hospital: this.hospital,
            state: this.gugunCode,
            sort: "gugun",
            select: this.selectedSort,
          });
        } else if (this.sidoCode) {
          this.getHospitalListBySort({
            hospital: this.hospital,
            state: this.sidoCode,
            sort: "sido",
            select: this.selectedSort,
          });
        } else {
          this.getHospitalListBySort({
            hospital: this.hospital,
            state: "null",
            select: this.selectedSort,
          });
        }
      }
    },
  },
};
</script>
<style></style>
