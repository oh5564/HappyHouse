<template>
  <b-tr class="board-list">
    <b-td
      ><span id="text-td">{{ articleno }}</span></b-td
    >
    <b-th class="text-left" id="text1">
      <router-link
        :to="{ name: 'boardDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td
      ><span id="text-td">{{ hit }}</span></b-td
    >
    <b-td
      ><span id="text-td">{{ userid }}</span></b-td
    >
    <b-td
      ><span id="text-td">{{ regtime | dateFormat }}</span></b-td
    >
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style>
#text-td {
  color: #07305d;
}

.board-list {
  background-color: whitesmoke;
}
</style>
