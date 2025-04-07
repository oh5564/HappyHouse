<template>
  <div id="app">
    <navi-bar />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
@font-face {
  font-family: "NanumSquareRoundB";
  src: url("@/assets/fonts/NanumSquareRoundB.ttf") format("truetype");
}

@font-face {
  font-family: "NanumSquareRoundL";
  src: url("@/assets/fonts/NanumSquareRoundL.ttf") format("truetype");
}

@font-face {
  font-family: "NanumSquareRoundEB";
  src: url("@/assets/fonts/NanumSquareRoundEB.ttf") format("truetype");
}

@font-face {
  font-family: "NanumSquareRoundR";
  src: url("@/assets/fonts/NanumSquareRoundR.ttf") format("truetype");
}

#app {
  font-family: NanumSquareRoundR, sans-serif;
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  height: 100%;
  background-color: #07305d; /* 부모 index.html에서 100 100을 지정해야 배경화면 채우기 가능 */
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  /* color: #07305d; */
  color: #07305d;
}

nav a.router-link-exact-active {
  /* color: #bbdfcf; */
  color: #d39559;
}
</style>
