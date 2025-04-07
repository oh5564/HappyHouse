<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">문의 작성</h3>
      </b-col>
    </b-row>
    <qna-input-item type="register" />
  </b-container>
</template>

<script>
import QnaInputItem from "@/components/qna/item/QnaInputItem.vue";

export default {
  name: "QnaWrite",
  components: {
    QnaInputItem,
  },
};
</script>

<style>
#text1 {
  color: whitesmoke;
}
</style>
