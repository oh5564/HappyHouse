<template>
  <div>
    <!--main banner image start-->
    <b-navbar-brand href="#" class="main-header">
      <router-link to="/">
        <img
          src="@/assets/mainlogo_crop.png"
          class="main-logo d-inline-block align-middle"
          height="80px"
          alt="ssafy" />
      </router-link>
    </b-navbar-brand>
    <!--main banner image end-->
    <!--menu bar start-->
    <div>
      <b-navbar toggleable="lg" type="dark" variant="white">
        <!-- variant="transparent" -->
        <!-- <b-navbar-brand href="#">
          <router-link to="/">
            <img
              src="@/assets/ssafy_logo.png"
              class="d-inline-block align-middle"
              width="100px"
              alt="ssafy" />
          </router-link>
        </b-navbar-brand> -->

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="#"
              ><router-link :to="{ name: 'home' }" class="link"
                >홈</router-link
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><router-link :to="{ name: 'board' }" class="link"
                >공지사항</router-link
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><router-link :to="{ name: 'house' }" class="link"
                >아파트정보</router-link
              ></b-nav-item
            >
            <!-- <b-nav-item href="#"
              ><router-link :to="{ name: 'housetemp' }" class="link"
                ><b-icon icon="house-fill" font-scale="1.5"></b-icon>
                아파트정보(backup)</router-link
              ></b-nav-item
            > -->
            <!--Q&A -->
            <b-nav-item href="#"
              ><router-link :to="{ name: 'qna' }" class="link">
                Q&A</router-link
              ></b-nav-item
            >
          </b-navbar-nav>
          <!--회원정보 -->

          <!-- <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <template #button-content>
                <b-icon icon="people" font-scale="2"></b-icon>
              </template>
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'signUp' }" class="link"
                  ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'signIn' }" class="link"
                  ><b-icon icon="key"></b-icon> 로그인</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'favorite' }" class="link"
                  ><b-icon icon="suit-heart"></b-icon> 찜</router-link
                ></b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav> -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <!--v-if-->
            <b-nav-item class="align-self-center text-dark"
              ><b-avatar
                variant="dark"
                v-text="
                  userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''
                "></b-avatar>
              <span id="welcome-text"
                >{{ userInfo.username }}({{ userInfo.userid }})님
                환영합니다.</span
              >
            </b-nav-item>
            <b-nav-item class="align-self-center"
              ><router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center"
                >내 정보 보기</router-link
              ></b-nav-item
            >
            <!-- <b-nav-item
              class="align-self-center"
              v-if="userInfo.userid === 'admin'"
              ><router-link
                :to="{ name: 'memberList' }"
                class="link align-self-center"
                >회원 목록 보기</router-link
              ></b-nav-item
            > -->
            <!-- <b-nav-item class="link align-self-center" href="#" v-if="userInfo"
              ><router-link :to="{ name: 'favorite' }"
                ><b-icon icon="pin" font-scale="1.5"></b-icon> 찜</router-link
              ></b-nav-item
            > -->
            <b-nav-item
              class="link align-self-center"
              @click.prevent="onClickLogout"
              ><span id="logout-text">로그아웃</span></b-nav-item
            >
          </b-navbar-nav>
          <b-navbar-nav class="navbar-dropdown ml-auto" v-else>
            <!--v-else-->
            <b-nav-item-dropdown right>
              <template #button-content>
                <b-icon
                  icon="people-fill"
                  font-scale="2"
                  variant="dark"></b-icon>
              </template>
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'signUp' }" class="link"
                  ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'signIn' }" class="link"
                  ><b-icon icon="key"></b-icon> 로그인</router-link
                ></b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <!--menu bar end-->
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
.main-header {
  background-color: #07305d;
}

.main-logo {
  margin-top: 10%;
  margin-bottom: 10%;
}

#welcome-text {
  margin-left: 10px;
  color: #07305d !important;
}

#logout-text {
  color: #07305d !important;
}

/* .b-nav-bar{
  background-color: white;
} */

/* .navbar-dropdown{
  background-color: #07305d;
} */
</style>
