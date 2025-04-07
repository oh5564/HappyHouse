<template>
  <div>
    <h2>신도시</h2>
    <b-container
      v-if="SinInfos && SinInfos.length != 0"
      class="bv-example-row mt-3">
      <b-table dark striped :items="temp_list" :fields="fields"></b-table>
      <div v-if="SinInfos">
        <b-button
          pill
          variant="info"
          v-for="(item, index) in showinfo"
          :key="index"
          @click="showlist(item)">
          {{ item }}
        </b-button>
      </div>
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
  name: "HouseAvgListSinCity",
  data() {
    return {
      temp_list: [],
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
    ...mapState(houseStore, ["SinInfos"]),
    showinfo() {
      let temp = this.SinInfos;
      let a = [...new Set(temp.map((v) => v.gugunName))];
      return a;
    },
    SinInfos() {
      this.$store.state.houseStore.SinInfos.forEach((element) => {
        element.avg = this.price(parseInt(element.avg) * 10000) + "원";
      });
      return this.$store.state.houseStore.SinInfos;
    },
  },
  methods: {
    showlist(value) {
      this.temp_list = [];
      for (let i = 0; i < this.SinInfos.length; i++) {
        if (this.SinInfos[i].gugunName === value) {
          this.temp_list.push(this.SinInfos[i]);
        }
      }
    },
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
