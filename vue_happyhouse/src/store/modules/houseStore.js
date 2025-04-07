import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListApt,
  AvgList,
  AvgListSinCity,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    dong: null,
    houses: [],
    house: "",
    gugun: "",
    sido: "",
    infos: [],
    SinInfos: [],
  },

  getters: {
    getHouses: (state) => {
      return state.houses.length;
    },
    getDong: (state) => {
      return state.dong;
    },
  },

  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_INFOS(state, infos) {
      state.infos = infos;
    },
    SET_SIN_INFOS(state, SinInfos) {
      state.SinInfos = SinInfos;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG(state) {
      state.dong = null;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_DONG(state, value) {
      state.dong = value;
    },
    SET_GUGUN(state, value) {
      state.gugun = value;
    },
    SET_SIDO(state, value) {
      state.siido = value;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList({ commit }, dongCode) {
      const params = {
        dong: dongCode,
      };
      houseList(
        params,
        ({ data }) => {
          //console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseListApt({ commit }, info) {
      const params = {
        apt: info.apt,
        state: info.state,
      };
      houseListApt(
        params,
        ({ data }) => {
          //console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getAvgList({ commit }, info) {
      const params = {
        dong: info.dong,
        min: info.min,
        max: info.max,
      };
      AvgList(
        params,
        ({ data }) => {
          commit("SET_INFOS", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getAvgListSinCity({ commit }, info) {
      const params = {
        min: info.min,
        max: info.max,
      };
      AvgListSinCity(
        params,
        ({ data }) => {
          commit("SET_SIN_INFOS", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse({ commit }, house) {
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
