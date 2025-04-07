<template>
  <!-- <div class="homebg">
    <div class="homeimg">
      <h1 class="maintext">건강과 함께하는 나의 집</h1>
      <img center class="mainimage" src="@/assets/houseimg/whitebuilding2.jpg" alt="Center image" />
    </div>
    <div class="homefooter">
      <h3>wow</h3>
    </div>
  </div> -->
  <div class="homebg mb-3">
    <div>
      <b-card
        class="main-b-card"
        overlay
        :img-src="whitebuildingimage"
        img-alt="Card Image"
        text-variant="#1A3C40;">
        <b-card-text class="h2 mt-5 text-center" id="text-1">
          모두가 행복한 집
        </b-card-text>
        <b-card-text class="mt-3 text-center" id="text-2">
          건강과 함께하는 행복한 집, Happyhouse
        </b-card-text>
      </b-card>
    </div>
    <div class="mt-1 ml-5 text-left" id="text-3">
      <b-row
        ><img
          src="@/assets/ssafy_logo.png"
          class="d-inline-block align-middle"
          width="90px"
          alt="ssafy" />
        <p class="mt-3">
          &copy; 2022 Happyhouse - SSAFY 광주 5반 김주희, 오철환
        </p>
      </b-row>
    </div>
  </div>
</template>

<script>
import whitebuildingimage from "@/assets/houseimg/whitebuilding4.jpg";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  data() {
    return {
      whitebuildingimage,
    };
  },
};
</script>

<style>
/* .underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
} */

/* .homebg {
  width: 100vw;
  height: 100vh;
  background-color: #07305d;
} */

.main-b-card {
  filter: brightness(90%);
  /* background: linear-gradient( to top, rgba(0,0,0,1) 100%, rgba(0,0,0,1) 75%, rgba(0,0,0,1) 50%, rgba(0,0,0,1) 40%, rgba(0,0,0,1) 25%, rgba(0,0,0,1) 10% );  */
  /* background-color: #07305d; */
}

/* .mainimage {
  width: 100%;
  height: 80%;
  object-fit: cover;
} */

/* .home-footer{
  background-color: #07305d;
} */

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

#text-1 {
  /* color: red; */
  font-family: NanumSquareRoundB, sans-serif;
}

#text-2 {
  font-family: NanumSquareRoundR, sans-serif;
}

#text-3 {
  font-family: NanumSquareRoundR, sans-serif;
  font-size: 90%;
  color: whitesmoke;
  /* color: #07305d; */
}
</style>
