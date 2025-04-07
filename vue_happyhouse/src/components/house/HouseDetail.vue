<template>
  <b-container v-if="house" class="">
    <b-row>
      <b-col
        style="
          padding: 7px 0;
          border-top: 1px dotted #bbb;
          width: 100%;
          height: 300px;
        "
        ><b-img
          :src="require(`@/assets/apts/${rand}.jpg`)"
          style="width: 100%; height: 100%; object-fit: cover"></b-img
      ></b-col>
    </b-row>
    <div id="list-hs-text" style="padding: 7px 0">
      <div
        style="font-weight: bold; padding: 7px 0; border-top: 1px dotted #bbb">
        아파트 이름 : {{ house.apartmentName }}
      </div>
      <div
        style="font-weight: bold; border-top: 1px dotted #bbb; padding: 7px 0">
        거래금액 :
        {{ (parseInt(house.recentPrice.replace(",", "")) * 10000) | price }}원(
        {{ house.sellYear }})
      </div>
      <div style="border-top: 1px dotted #bbb; padding: 7px 0">
        법정동 : {{ house.dongName }}
      </div>
      <div style="border-top: 1px dotted #bbb; padding: 7px 0">
        건설년도 : {{ house.buildYear }}년
      </div>
      <div style="border-top: 1px dotted #bbb; padding: 7px 0">
        층수 : {{ house.floor }}층
      </div>
    </div>
    <!-- <b-row>
      <b-col>
        <b-alert show variant="primary" class="font-weight-bold"
          >아파트 이름 : {{ house.apartmentName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger" class="font-weight-bold"
          >거래금액 :
          {{
            (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
          }}원( {{ house.sellYear }})</b-alert
        >
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
        <b-alert show variant="primary">층수 : {{ house.floor }}층</b-alert>
      </b-col>
    </b-row> -->
    <!-- <b-row>
      <b-col>
        <b-button class="favorite-btn w-120">
          <b-icon icon="heart"></b-icon> 찜하기
        </b-button>
      </b-col>
    </b-row> -->
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
/* .favorite-btn .fa-icon {
  vertical-align: middle;
  margin-right: 0.5rem;
}

.favorite-btn .fa-icon:last-child {
  margin-right: 0;
} */
#list-hs-text {
  color: #07305d;
}
</style>
