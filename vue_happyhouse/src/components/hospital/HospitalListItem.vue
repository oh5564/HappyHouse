<template>
  <b-row
    @click="selectHospital"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    style="text-align: center">
    <b-col
      class="align-self-center font-weight-bold"
      style="color: black; padding: 0; margin: 0"
      >{{ hospital.yadmNm }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
const hospitalStore = "hospitalStore";
export default {
  name: "HospitalListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    hospital: Object,
  },
  methods: {
    ...mapActions(hospitalStore, ["detailHospital"]),
    selectHospital() {
      this.detailHospital(this.hospital);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
