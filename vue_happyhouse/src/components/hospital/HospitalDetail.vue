<template>
  <b-container v-if="hospital" class="">
    <!-- <b-row>
      <b-col
        ><b-img :src="require(`@/assets/houseimg/whitebuilding4.jpg`)" fluid-grow></b-img
      ></b-col>
    </b-row> -->
    <div id="list-hp-text" style="padding: 7px 0">
      <div style="font-weight: bold; padding: 7px 0">
        병원 이름 : {{ hospital.yadmNm }}
      </div>
      <div style="padding: 7px 0; border-top: 1px dotted #bbb">
        주소 : {{ hospital.addr }}
      </div>
      <div
        style="font-weight: bold; border-top: 1px dotted #bbb; padding: 7px 0">
        전화번호 : {{ hospital.telno }}
      </div>
      <div style="padding: 7px 0; border-top: 1px dotted #bbb">
        홈페이지: {{ hospital.hospUrl }}
      </div>
      <div style="padding: 7px 0; border-top: 1px dotted #bbb">
        분류 : {{ hospital.clCdNm }}
      </div>
    </div>
    <!-- <b-row>
      <b-col>
        <b-alert show variant="primary" class="font-weight-bold"
          >병원 이름 : {{ hospital.yadmNm }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">주소 : {{ hospital.addr }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning" class="font-weight-bold"
          >전화번호 : {{ hospital.telno }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger"
          >홈페이지: {{ hospital.hospUrl }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">분류 : {{ hospital.clCdNm }}</b-alert>
      </b-col>
    </b-row> -->
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const hospitalStore = "hospitalStore";
export default {
  name: "HospitalDetail",
  computed: {
    ...mapState(hospitalStore, ["hospital"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  // filters: {
  //   price(value) {
  //     if (!value) return value;
  //     return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  //   },
  // },
};
</script>

<style>
#list-hp-text {
  color: #07305d;
}
</style>
