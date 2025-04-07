<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text2">문의 목록</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right mb-3">
        <b-button variant="secondary" @click="moveWrite()">문의 등록</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>번호</b-th>
              <b-th>제목</b-th>
              <!-- <b-th>내용</b-th> -->
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
              <b-th>답변개수</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <qna-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article" />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import QnaListItem from "@/components/qna/item/QnaListItem";

export default {
  name: "QnaList",
  components: {
    QnaListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    http.get(`/qna/list`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}

#text2 {
  color: whitesmoke;
  /* color: #07305d; */
}
</style>
