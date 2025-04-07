<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <h3 id="text2">공지사항</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right mb-3">
        <b-button variant="secondary" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple>
          <!--hover responsive-->
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article" />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveWrite() {
      if (this.userInfo.userid != "admin") {
        alert("관리자만 작성할 수 있습니다.");
      } else {
        this.$router.push({ name: "boardRegister" });
      }
    },
  },
};
</script>

<style>
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
