import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function houseListApt(params, success, fail) {
  api.get(`/map/searchapt`, { params: params }).then(success).catch(fail);
}

function AvgList(params, success, fail) {
  api.get(`/map/avglist`, { params: params }).then(success).catch(fail);
}

function AvgListSinCity(params, success, fail) {
  api.get(`/map/avglistSinCity`, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListApt,
  AvgList,
  AvgListSinCity,
};
