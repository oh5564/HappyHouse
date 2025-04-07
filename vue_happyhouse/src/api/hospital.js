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

//======================================================================
import axios from "axios";

function houseApi() {
  const instance = axios.create({
    baseURL:
      "https://apis.data.go.kr/B551182/hospInfoService1/getHospBasisList1?serviceKey=Ht5iESDZYJ6sJE7axiiyD5xTxBWVWfu7LD9tU4tN3n%2BuEAhKyx39WmThwPReLfBL%2B2vVP%2FI6%2BucF%2B%2FQs8vYtzg%3D%3D",
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
const houseapi = houseApi();

function hospitalList(success, fail) {
  //console.log("houseapi: ", houseapi);
  houseapi
    .get("", { params: { pageNo: 1, numOfRows: 10 } })
    .then(success)
    .catch(fail);
}

function hospitalListbyDong(params, success, fail) {
  //console.log("params: ", params);
  houseapi.get("", { params: params }).then(success).catch(fail);
}

function hospitalListByName(params, success, fail) {
  //console.log("params: ", params);
  houseapi.get("", { params: params }).then(success).catch(fail);
}

function hospitalListBySort(params, success, fail) {
  //console.log("params: ", params);
  houseapi.get("", { params: params }).then(success).catch(fail);
}

//=======================================================================
export {
  sidoList,
  gugunList,
  dongList,
  hospitalList,
  hospitalListbyDong,
  hospitalListByName,
  hospitalListBySort,
};
