import {
  sidoList,
  gugunList,
  dongList,
  hospitalList,
  hospitalListbyDong,
  hospitalListByName,
  hospitalListBySort,
} from "@/api/hospital.js";

const hospitalStore = {
  namespaced: true,
  state: {
    sidos_HP: [{ value: null, text: "선택하세요" }],
    guguns_HP: [{ value: null, text: "선택하세요" }],
    dongs_HP: [{ value: null, text: "선택하세요" }],
    dong_HP: "",
    hospitals: [],
    hospital: "",
    gugun_HP: "",
    sido_HP: "",
    infos_HP: [],
  },
  getters: {
    getHospitals: (state) => {
      return state.hospitals.length;
    },
    getDong: (state) => {
      return state.dong_HP;
    },
  },
  mutations: {
    SET_SIDO_LIST_HP(state, sidos_HP) {
      sidos_HP.forEach((sido_HP) => {
        state.sidos_HP.push({
          value: sido_HP.sidoCode,
          text: sido_HP.sidoName,
        });
      });
    },
    SET_GUGUN_LIST_HP(state, guguns_HP) {
      guguns_HP.forEach((gugun_HP) => {
        state.guguns_HP.push({
          value: gugun_HP.gugunCode,
          text: gugun_HP.gugunName,
        });
      });
    },
    SET_DONG_LIST_HP(state, dongs_HP) {
      dongs_HP.forEach((dong_HP) => {
        //console.log("dong_HP: ", dong_HP);
        state.dongs_HP.push({
          value: dong_HP.dongCode, //dongName
          text: dong_HP.dongName,
        });
      });
    },
    SET_INFOS_HP(state, infos) {
      state.infos = infos;
    },
    CLEAR_SIDO_LIST_HP(state) {
      state.sidos_HP = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST_HP(state) {
      state.guguns_HP = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST_HP(state) {
      state.dongs_HP = [{ value: null, text: "선택하세요" }];
    },
    SET_DONG_HP(state, value) {
      state.dong_HP = value;
    },
    SET_GUGUN_HP(state, value) {
      state.gugun_HP = value;
    },
    SET_SIDO_HP(state, value) {
      state.sido_HP = value;
    },
    SET_HOSPITAL_LIST_HP(state, hospitals) {
      state.hospitals = hospitals;
    },
    SET_DETAIL_HOSPITAL_HP(state, hospital) {
      state.hospital = hospital;
    },
  },
  actions: {
    //=============시군구 actions================================
    getSido_HP: ({ commit }) => {
      sidoList(
        ({ data }) => {
          //console.log(data);
          commit("SET_SIDO_LIST_HP", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getGugun_HP: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST_HP", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getDong_HP: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          //console.log("data: ", data);
          commit("SET_DONG_LIST_HP", data);
        },
        (error) => {
          console.log(error); ///error
        },
      );
    },

    //=============General hospital actions=======================
    getHospitalList: ({ commit }) => {
      //0. 전체 리스트 반환(테스트)
      hospitalList(
        ({ data }) => {
          console.log(data);
          console.log(commit);
          //commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getHospitalListbyDong({ commit }, dongName) {
      //1. 일반 병원 지역명으로 검색 (동코드)
      const params = {
        emdongNm: dongName,
      };
      hospitalListbyDong(
        params,
        ({ data }) => {
          //console.log("data: ", data);
          //console.log("첫번째 데이터: ", data.response.body.items.item[0]);
          commit("SET_HOSPITAL_LIST_HP", data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getHospitalListByName({ commit }, info) {
      let params = {};

      if (info.sort == "dong") {
        params = {
          yadmNm: info.hospital,
          emdongNm: info.state,
        };
      } else if (info.sort == "gugun") {
        params = {
          yadmNm: info.hospital,
          sgguCd: info.state,
        };
      } else if (info.sort == "sido") {
        params = {
          yadmNm: info.hospital,
          sidoCd: info.state,
        };
      } else {
        params = {
          yadmNm: info.hospital,
        };
      }
      hospitalListByName(
        params,
        ({ data }) => {
          //console.log("첫번째text: ", data.response.body);
          //console.log("첫번째 데이터: ", data.response.body.items.item);
          commit("SET_HOSPITAL_LIST_HP", data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHospitalListBySort({ commit }, info) {
      let params = {};

      if (info.sort == "dong") {
        params = {
          yadmNm: info.hospital,
          emdongNm: info.state,
          dgsbjtCd: info.select,
        };
      } else if (info.sort == "gugun") {
        params = {
          yadmNm: info.hospital,
          sgguCd: info.state,
          dgsbjtCd: info.select,
        };
      } else if (info.sort == "sido") {
        params = {
          yadmNm: info.hospital,
          sidoCd: info.state,
          dgsbjtCd: info.select,
        };
      } else {
        if (info.hospital) {
          params = {
            yadmNm: info.hospital,
            dgsbjtCd: info.select,
          };
        } else {
          params = {
            dgsbjtCd: info.select,
          };
        }
      }
      hospitalListBySort(
        params,
        ({ data }) => {
          // console.log("첫번째text: ", data.response.body.items);
          // console.log("첫번째 데이터: ", data.response.body.items.item);
          // console.log("첫번째 데이터2: ", data.response.body.items.item[0]);
          if (data.response.body.items.item[1] == null) {
            commit("SET_HOSPITAL_LIST_HP", data.response.body.items);
          } else {
            commit("SET_HOSPITAL_LIST_HP", data.response.body.items.item);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHospital({ commit }, hospital) {
      commit("SET_DETAIL_HOSPITAL_HP", hospital);
    },

    //=============Pharmacy actions===============================

    //=============Covid hospital actions=========================
  },
};

export default hospitalStore;
