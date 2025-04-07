<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col
        ><h3>{{ house.apartmentName }}</h3></b-col
      >
    </b-row>
    <b-row class="mb-2 mt-1">
      <b-col
        ><b-img :src="require(`@/assets/apts/${rand}.jpg`)" fluid-grow></b-img
      ></b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.apartmentName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">법정동 : {{ house.dongName }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning"
          >건설년도 : {{ house.buildYear }}년</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning">층수 : {{ house.floor }}층</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger"
          >거래금액 :
          {{
            (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
          }}원</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">거래일자 : {{ house.sellYear }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-button class="favorite-btn w-120">
          <b-icon icon="heart"></b-icon> 찜하기
        </b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseDetail2",

  computed: {
    ...mapState(houseStore, ["house"]),
    rand() {
      let a = this.house.floor;
      return Math.floor(Math.random() * (10 - 1 + 1)) + 1 + a - a;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style>
.favorite-btn .fa-icon {
  vertical-align: middle;
  margin-right: 0.5rem;
}

.favorite-btn .fa-icon:last-child {
  margin-right: 0;
}
</style>
