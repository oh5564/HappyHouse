<template>
  <div>
    <div v-if="houses && houses.length != 0">
      <h4 id="text-house-list">주택 목록</h4>
      <house-list-item
        v-for="(house, index) in pageinatedData"
        :key="index"
        :house="house" />
    </div>
    <div v-else>
      <b-row>
        <b-col id="text-house-list">주택 목록이 없습니다.</b-col>
      </b-row>
    </div>
    <div
      class="btn-cover"
      v-if="houses && houses.length != 0"
      style="color: black">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn">
        다음
      </button>
    </div>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  created() {
    this.$store.state.houseStore.house = null;
    this.$store.state.houseStore.houses = [];
    this.$store.state.houseStore.infos = [];
    this.$store.state.houseStore.SinInfos = [];
  },
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      pageNum: 0,
    };
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 6,
    },
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
    pageCount() {
      let listLeng = this.houses.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng / listSize > 0) page += 1;

      return page;
    },
    pageinatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;

      return this.houses.slice(start, end);
    },
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
};
</script>

<style>
#text-house-list {
  color: #d39559;
  font-family: NanumSquareRoundB, sans-serif;
}
</style>
