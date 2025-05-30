import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        //beforeEnter: onlyAuthUser, // 인증 값 검증 로직 추가
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
      {
        path: "memberregister",
        name: "memberRegister",
        beforeEnter: onlyAuthUser, // 인증 값 검증 로직 추가
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "membermodify",
        name: "memberModify",
        beforeEnter: onlyAuthUser, // 인증 값 검증 로직 추가
        component: () => import("@/components/user/MemberModify.vue"),
      },
      {
        path: "memberlist",
        name: "memberList",
        beforeEnter: onlyAuthUser, // 인증 값 검증 로직 추가
        component: () => import("@/components/user/MemberList.vue"),
      },
      {
        path: "memberdelete",
        name: "memberDelete",
        //beforeEnter: onlyAuthUser, // 인증 값 검증 로직 추가
        component: () => import("@/components/user/MemberDelete.vue"),
      },
      {
        path: "memberregisterresult",
        name: "memberRegisterResult",
        component: () =>
          import("@/components/user/result/MemberRegisterResult.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/housetemp",
    name: "housetemp",
    component: () => import("@/views/HouseTempView.vue"),
  },
  {
    //매물 찜
    path: "/favorite",
    name: "favorite",
    component: () => import("@/views/FavoriteView.vue"),
  },
  {
    //병원 정보(임시)
    path: "/hospital",
    name: "hospital",
    component: () => import("@/views/HospitalView.vue"),
  },
  {
    //Q&A
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/qnalist",
    children: [
      {
        path: "qnalist",
        name: "qnaList",
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "qnawrite",
        name: "qnaRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaRegister.vue"),
      },
      {
        path: "qnadetail/:articleno",
        name: "qnaDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaDetail.vue"),
        children: [
          {
            path: "answerinput",
            name: "answerInput",
            component: () => import("@/components/qna/answer/AnswerInput.vue"),
            props: true,
          },
          {
            path: "answerlist",
            name: "answerList",
            component: () => import("@/components/qna/answer/AnswerList.vue"),
            props: true,
          },
        ],
      },
      {
        path: "qnamodify/:articleno",
        name: "qnaModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaModify.vue"),
      },
      {
        path: "qnadelete/:articleno",
        name: "qnaDelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaDelete.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
