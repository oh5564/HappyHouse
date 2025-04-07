<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text1">문의 수정</h3>
      </b-col>
    </b-row>
    <qna-input-item type="modify" />
  </b-container>
</template>

<script>
import QnaInputItem from "@/components/qna/item/QnaInputItem.vue";

export default {
  name: "QnaModify",
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
