import Vue from "vue";
import Vuex from "vuex";
//import http from "@/api/http";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import boardStore from "@/store/modules/boardStore.js";
import houseStore from "@/store/modules/houseStore.js";
import qnaStore from "@/store/modules/qnaStore.js";
import hospitalStore from "@/store/modules/hospitalStore.js";

const store = new Vuex.Store({
  modules: {
    memberStore,
    boardStore,
    houseStore,
    qnaStore,
    hospitalStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;

// export default new Vuex.Store({
//   state: {
//     sidos: [{ value: null, text: "선택하세요" }],
//     guguns: [{ value: null, text: "선택하세요" }],
//     dongs: [{ value: null, text: "선택하세요" }],
//     houses: [],
//     house: null,
//     todos: [
//       // { title: '할 일1', completed: false },
//       // { title: '할 일2', completed: false },
//     ],
//     answers: [],
//   },
//   getters: {
//     allTodosCount(state) {
//       return state.todos.length;
//     },
//     completedTodosCount(state) {
//       return state.todos.filter((todo) => {
//         return todo.completed === true;
//       }).length;
//     },
//     unCompletedTodosCount(state) {
//       return state.todos.filter((todo) => {
//         return todo.completed === false;
//       }).length;
//     },
//     allAnswersCount(state) {
//       return state.answers.length;
//     },
//   },
//   mutations: {
//     /////////////////////////////// House start /////////////////////////////////////
//     SET_SIDO_LIST(state, sidos) {
//       sidos.forEach((sido) => {
//         state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
//       });
//     },
//     SET_GUGUN_LIST(state, guguns) {
//       guguns.forEach((gugun) => {
//         state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
//       });
//     },
//     SET_DONG_LIST(state, dongs) {
//       dongs.forEach((dong) => {
//         state.dongs.push({ value: dong.dongCode, text: dong.dongName });
//       });
//     },
//     CLEAR_SIDO_LIST(state) {
//       state.sidos = [{ value: null, text: "선택하세요" }];
//     },
//     CLEAR_GUGUN_LIST(state) {
//       state.guguns = [{ value: null, text: "선택하세요" }];
//     },
//     CLEAR_DONG_LIST(state) {
//       state.dongs = [{ value: null, text: "선택하세요" }];
//     },
//     SET_HOUSE_LIST(state, houses) {
//       state.houses = houses;
//     },
//     SET_DETAIL_HOUSE(state, house) {
//       console.log("Mutations", house);
//       state.house = house;
//     },
//     /////////////////////////////// House end /////////////////////////////////////

//     //////////////////////////// Todo List start //////////////////////////////////
//     CREATE_TODO(state, todoItem) {
//       state.todos.push(todoItem);
//     },
//     DELETE_TODO(state, todoItem) {
//       const index = state.todos.indexOf(todoItem);
//       state.todos.splice(index, 1);
//     },
//     UPDATE_TODO_STATUS(state, todoItem) {
//       state.todos = state.todos.map((todo) => {
//         if (todo === todoItem) {
//           return {
//             ...todo,
//             completed: !todoItem.completed, //toggle
//           };
//         }
//         return todo;
//       });
//     },
//     //////////////////////////// Todo List end //////////////////////////////////

//     //////////////////////////// Q&A Answer List start //////////////////////////////////
//     CREATE_ANSWER(state, answerItem) {
//       state.answers.push(answerItem);
//     },
//     DELETE_ANSWER(state, answerItem) {
//       const index = state.answers.indexOf(answerItem);
//       state.answers.splice(index, 1);
//     },
//     UPDATE_ANSWER_STATUS(state, answerItem) {
//       state.answers = state.answers.map((answer) => {
//         if (answer === answerItem) {
//           return {
//             ...answer,
//             completed: !answerItem.completed, //toggle
//           };
//         }
//         return answer;
//       });
//     },
//     //////////////////////////// Todo List end //////////////////////////////////
//   },
//   actions: {
//     /////////////////////////////// House start /////////////////////////////////////
//     getSido({ commit }) {
//       http
//         .get(`/map/sido`)
//         .then(({ data }) => {
//           // console.log(data);
//           commit("SET_SIDO_LIST", data);
//         })
//         .catch((error) => {
//           console.log(error);
//         });
//     },
//     getGugun({ commit }, sidoCode) {
//       const params = { sido: sidoCode };
//       http
//         .get(`/map/gugun`, { params })
//         .then(({ data }) => {
//           // console.log(commit, response);
//           commit("SET_GUGUN_LIST", data);
//         })
//         .catch((error) => {
//           console.log(error);
//         });
//     },
//     getDong({ commit }, gugunCode) {
//       console.log(gugunCode);
//       const params = { gugun: gugunCode };
//       http
//         .get(`/map/dong`, { params })
//         .then(({ data }) => {
//           // console.log(commit, response);
//           commit("SET_DONG_LIST", data);
//         })
//         .catch((error) => {
//           console.log(error);
//         });
//     },
//     getHouseList({ commit }, dongCode) {
//       const code = dongCode.substr(0, 5);
//       let dongName = "";
//       this.state.dongs.forEach((dong) => {
//         if (dong.value == dongCode) {
//           dongName = dong.text;
//         }
//       });
//       //console.log(code);
//       // vue cli enviroment variables 검색
//       //.env.local file 생성.
//       // 반드시 VUE_APP으로 시작해야 한다.
//       //const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
//       const SERVICE_KEY =
//         "8fVRgonILA032ROUTNnNdQu3UMAQuUr%2Fg1P1QrdBSfNTIWYw9%2FuCs65Zk1oVM3slI3up7MwZZdYJLD142wKpmQ%3D%3D";
//       const SERVICE_URL =
//         "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade";
//       const params = {
//         LAWD_CD: code,
//         DEAL_YMD: "201503",
//         serviceKey: decodeURIComponent(SERVICE_KEY),
//       };
//       http
//         .get(SERVICE_URL, { params })
//         .then(({ data }) => {
//           const dd = data.response.body.items.item;
//           let list = [];
//           dd.forEach((d) => {
//             if (d.법정동.trim() == dongName) {
//               list.push(d);
//             }
//           });
//           console.log(list);
//           commit("SET_HOUSE_LIST", list);
//         })
//         .catch((error) => {
//           console.log(error);
//         });
//     },
//     detailHouse({ commit }, house) {
//       // 나중에 house.일련번호를 이용하여 API 호출
//       // console.log(commit, house);
//       commit("SET_DETAIL_HOUSE", house);
//     },
//     /////////////////////////////// House end /////////////////////////////////////

//     //////////////////////////// Todo List start //////////////////////////////////

//     // destructuring 활용
//     createTodo({ commit }, todoItem) {
//       commit("CREATE_TODO", todoItem);
//     },
//     deleteTodo({ commit }, todoItem) {
//       commit("DELETE_TODO", todoItem);
//     },
//     updateTodoStatus({ commit }, todoItem) {
//       commit("UPDATE_TODO_STATUS", todoItem);
//     },
//     //////////////////////////// Todo List end //////////////////////////////////

//     //////////////////////////// Qna Answer List start //////////////////////////////////

//     // destructuring 활용
//     createAnswer({ commit }, answerItem) {
//       commit("CREATE_ANSWER", answerItem);
//     },
//     deleteAnswer({ commit }, answerItem) {
//       commit("DELETE_ANSWER", answerItem);
//     },
//     updateAnswerStatus({ commit }, answerItem) {
//       commit("UPDATE_ANSWER_STATUS", answerItem);
//     },
//     //////////////////////////// Todo List end //////////////////////////////////
//   },
//   modules: {},
//   plugins: [
//     createPersistedState({
//       // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
//       storage: sessionStorage,
//     }),
//   ],
// });
