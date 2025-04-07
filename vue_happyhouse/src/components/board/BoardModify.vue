<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">공지 수정</h3>
      </b-col>
    </b-row>
    <board-input-item type="modify" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}
</style>
