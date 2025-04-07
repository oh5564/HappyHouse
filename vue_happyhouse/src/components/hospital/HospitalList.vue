<template>
  <div>
    <div v-if="hospitals && hospitals.length != 0">
      <h4 id="text-hospital-list">병원 목록</h4>
      <hospital-list-item
        v-for="(hospital, index) in pageinatedData"
        :key="index"
        :hospital="hospital" />
    </div>
    <!-- <b-container v-else-if="hospitals && hospitals.length == 1" class="bv-example-row mt-3">
      <b-col><b-alert show>병원이 한개</b-alert></b-col>
    </b-container> -->
    <div v-else>
      <b-row>
        <b-col id="text-hospital-list">병원 정보가 없습니다.</b-col>
      </b-row>
    </div>
    <div
      class="btn-cover"
      v-if="hospitals && hospitals.length != 0"
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
import HospitalListItem from "@/components/hospital/HospitalListItem.vue";
import { mapState } from "vuex";

const hospitalStore = "hospitalStore";

export default {
  name: "HospitalList",
  created() {
    this.$store.state.hospitalStore.hospital = null;
    this.$store.state.hospitalStore.hospitals = [];
  },
  components: {
    HospitalListItem,
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
      default: 10,
    },
  },
  computed: {
    ...mapState(hospitalStore, ["hospitals"]),
    pageCount() {
      let listLeng = this.hospitals.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng / listSize > 0) page += 1;

      return page;
    },
    pageinatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;

      return this.hospitals.slice(start, end);
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
#text-hospital-list {
  color: #d39559;
  font-family: NanumSquareRoundB, sans-serif;
}
</style>
