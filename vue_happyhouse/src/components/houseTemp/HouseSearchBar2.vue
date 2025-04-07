<template>
  <div class="p-3 mb-2 bg-light text-dark rounded">
    <!--======================아파트 매매 결과 검색=========================-->
    <!--아파트 지역으로 검색-->
    <b-row class="mt-2 ml-3">
      <p><b-icon icon="house-fill"></b-icon> 아파트 매매 지역으로 검색</p>
    </b-row>
    <b-row class="mt-1 mb-4 text-center">
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
          @change="searchAptAndHospital($event)"></b-form-select>
      </b-col>
    </b-row>
    <b-row class="mt-2 ml-3">
      <p><b-icon icon="house-fill"></b-icon> 아파트 이름 & 평수로 검색</p>
    </b-row>
    <b-row>
      <!--아파트 이름으로 검색-->
      <b-col>
        <!-- <label for="apt">아파트별 검색:</label> -->
        <input type="text" v-model="apt" id="apt" @keydown.enter="aptlist" />
        <b-button @click="aptlist" variant="secondary">검색</b-button>
        <!-- <input type="button" value="검색" @click="aptlist" variant="secondary"/> -->
      </b-col>
      <!--아파트 평수로 검색-->
      <b-col>
        <label for="apt">평수: </label>
        <input type="number" v-model="min" id="pung" />
        <input type="number" v-model="max" id="pung" />
        <b-button @click="punglist" v-b-modal.modal-xl variant="secondary"
          >검색</b-button
        >
      </b-col>
      <!--평균 거래 내역 모달-->
      <div>
        <b-modal
          id="modal-xl"
          size="xl"
          title="평균 거래 내역"
          v-if="dongCode && min && max">
          <b-row>
            <b-col cols="6" align="left">
              <HouseAvgList />
            </b-col>
            <b-col cols="6">
              <HouseAvgListSinCity />
            </b-col>
          </b-row>
        </b-modal>
      </div>
      <!--병원, 약국, 선별진료소 검색-->
      <b-col>
        병원&약국:
        <b-form-select
          v-model="selected"
          :options="options"
          size="sm"
          class="w-50"></b-form-select>
      </b-col>
    </b-row>
    <!-- 병원 약국 메뉴 고를 시 검색창 등장 -->
    <!--일반 병원 -->
    <b-row class="mt-3 ml-3" v-if="selected == 'hospitalGeneral'">
      <p><b-icon icon="house-fill"></b-icon> 일반 병원 이름, 분류명으로 검색</p>
    </b-row>
    <b-row v-if="selected == 'hospitalGeneral'">
      <b-col>
        <input
          type="text"
          v-model="hospital"
          id="apt"
          @keydown.enter="searchHospitalByName" />
        <b-button @click="searchHospitalByName" variant="secondary"
          >검색</b-button
        >
      </b-col>
      <b-col>
        <b-form-select
          v-model="selectedSort"
          :options="optsSort"
          @change="searchHospitalBySort"></b-form-select>
      </b-col>
      <b-col></b-col>
    </b-row>
    <!-- 약국 -->
    <b-row class="mt-3 ml-3" v-if="selected == 'pharmacy'">
      <p><b-icon icon="house-fill"></b-icon> 약국 이름으로 검색</p>
    </b-row>
    <b-row v-if="selected == 'pharmacy'">
      <b-col>
        <input
          type="text"
          v-model="hospital"
          id="apt"
          @keydown.enter="searchHospitalByName" />
        <b-button @click="searchHospitalByName" variant="secondary"
          >검색</b-button
        >
      </b-col>
      <b-col></b-col><b-col></b-col>
    </b-row>
    <!-- 선별진료소 -->
    <b-row class="mt-3 ml-3" v-if="selected == 'hospitalCovid'">
      <p><b-icon icon="house-fill"></b-icon> 선별진료소 이름으로 검색</p>
    </b-row>
    <b-row v-if="selected == 'hospitalCovid'">
      <b-col>
        <input
          type="text"
          v-model="hospital"
          id="apt"
          @keydown.enter="searchHospitalByName" />
        <b-button @click="searchHospitalByName" variant="secondary"
          >검색</b-button
        >
      </b-col>
      <b-col></b-col><b-col></b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import HouseAvgList from "@/components/houseTemp/HouseAvgList2.vue";
import HouseAvgListSinCity from "@/components/houseTemp/HouseAvgListSinCity2.vue";
const houseStore = "houseStore";
const hospitalStore = "hospitalStore";

export default {
  name: "HouseSearchBar",
  components: {
    HouseAvgList,
    HouseAvgListSinCity,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      dongName: "",
      apt: "",
      hospital: "",
      min: 0,
      max: 0,
      viewed: false,
      selected: null,
      options: [
        { text: "분류 선택", value: null },
        { text: "일반 병원", value: "hospitalGeneral", disabled: false },
        { text: "약국", value: "pharmacy", disabled: false },
        {
          text: "코로나 선별진료소",
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
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "infos",
      "SinInfos",
    ]),
    ...mapState(hospitalStore, [
      "sidos_HP",
      "guguns_HP",
      "dongs_HP",
      "dong_HP",
      "hospitals",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    //state 초기화: mutations로 가야지: store/index.js
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_DONG();
    this.getSido();

    // this.CLEAR_SIDO_LIST_HP();
    // this.CLEAR_GUGUN_LIST_HP();
    // this.CLEAR_DONG_LIST_HP();
    // this.getSido_HP();
    // this.getDong_HP(); //----@
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getHouseListApt",
      "getAvgList",
      "getAvgListSinCity",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_DONG",
      "SET_SIDO",
      "SET_GUGUN",
      "SET_DONG",
    ]),
    ...mapActions(hospitalStore, [
      "getSido_HP",
      "getGugun_HP",
      "getDong_HP",
      // "getHospitalList",
      "getHospitalListbyDong",
      "getHospitalListByName",
      "getHospitalListBySort",
    ]),
    ...mapMutations(hospitalStore, [
      "CLEAR_SIDO_LIST_HP",
      "CLEAR_GUGUN_LIST_HP",
      "CLEAR_DONG_LIST_HP",
      "SET_SIDO_HP",
      "SET_GUGUN_HP",
      "SET_DONG_HP",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      //console.log(this.sidoCode);
      this.SET_SIDO(this.sidoCde);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);

      // this.SET_SIDO_HP(this.sidoCde);
      // this.CLEAR_GUGUN_LIST_HP();
      // this.CLEAR_DONG_LIST_HP();
      // this.gugunCode = null;
      // if (this.sidoCode) this.getGugun_HP(this.sidoCode);
    },
    dongList() {
      this.SET_GUGUN(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);

      // this.SET_GUGUN_HP(this.gugunCode);
      // this.CLEAR_DONG_LIST_HP();
      // this.dongCode = null;
      // if (this.gugunCode) this.getDong_HP(this.gugunCode);
    },
    searchAptAndHospital(event) {
      //동 선택 시 호출됨

      //console.log("this.dongcode: ", this.dongcode);
      // console.log("event: ", event);
      // console.log("this.dongs: ", this.dongs);

      let cName = "";
      //선택한 동의 이름 가져오기
      this.dongs.forEach((dong) => {
        //console.log("dong.text: ", dong.text); //동 이름
        //console.log("dong.value: ", dong.value); //동 코드

        if (dong.value == event) {
          cName = dong.text;
        }
      });

      //console.log("cName: ", cName);

      this.SET_DONG(event); //코드
      this.SET_DONG_HP(cName); //이름

      if (this.dongCode) {
        //1. 아파트 검색
        this.getHouseList(this.dongCode);

        //2. 병원 검색
        this.getHospitalListbyDong(cName);

        //3. 약국

        //4. 선별진료소 검색
      }
    },
    aptlist() {
      if (this.apt) {
        if (this.dongCode) {
          this.getHouseListApt({
            apt: this.apt,
            state: this.dongCode,
          });
        } else if (this.gugunCode) {
          this.getHouseListApt({
            apt: this.apt,
            state: this.gugunCode,
          });
        } else if (this.sidoCode) {
          this.getHouseListApt({
            apt: this.apt,
            state: this.sidoCode,
          });
        } else {
          this.getHouseListApt({
            apt: this.apt,
            state: "null",
          });
        }
      }
    },
    punglist() {
      if (this.max) {
        if (this.min) {
          if (this.dongCode) {
            this.getAvgList({
              dong: this.dongCode,
              min: this.min,
              max: this.max,
            });
            this.getAvgListSinCity({
              min: this.min,
              max: this.max,
            });
          } else {
            alert("동을 선택하세요!");
            return;
          }
        } else {
          alert("최소평수를 입력하세요!");
          return;
        }
      } else {
        alert("최대 평수를 입력하세요!");
        return;
      }
    },
    searchHospitalByName() {
      //console.log("dongName: ", this.dong_HP);
      let dongName = this.dong_HP;

      //2. 일반 병원 이름으로 조회
      if (this.hospital) {
        if (dongName) {
          this.getHospitalListByName({
            hospital: this.hospital,
            state: dongName,
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
      //console.log("selectedSort: ", this.selectedSort);
      let dongName = this.dong_HP;

      if (this.selectedSort) {
        if (dongName) {
          this.getHospitalListBySort({
            hospital: this.hospital,
            state: dongName,
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

<style>
#pung {
  width: 60px;
}
</style>
