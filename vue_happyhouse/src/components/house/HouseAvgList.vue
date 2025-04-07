<template>
  <div>
    <h2>{{ dongName }}</h2>
    <b-container v-if="infos && infos.length != 0" class="bv-example-row mt-3">
      <b-table striped hover :items="infos" :fields="fields"></b-table>
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>거래 내역이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseAvgList",
  data() {
    return {
      dong_name: "",
      fields: [
        {
          key: "gugunName",
          label: "구군명",
          sortable: false,
        },
        {
          key: "dealYear",
          label: "거래년도",
          sortable: true,
        },
        {
          key: "avg",
          label: "평균가격",
          sortable: true,
        },
        {
          key: "count",
          label: "거래횟수",
          sortable: true,
        },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["dong", "dongs"]),
    dongName() {
      this.dongs.forEach((element) => {
        if (element.value == this.dong) {
          this.dong_name = element.text;
        }
      });
      return this.dong_name;
    },
    infos() {
      this.$store.state.houseStore.infos.forEach((element) => {
        element.avg = this.price(parseInt(element.avg) * 10000) + "원";
      });
      return this.$store.state.houseStore.infos;
    },
  },
  methods: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
