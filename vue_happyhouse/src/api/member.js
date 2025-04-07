import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  console.log("user: ", user);
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
  // console.log("success: ", success);
  // console.log("fail: ", fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

// function logout(success, fail)

export { login, findById };
