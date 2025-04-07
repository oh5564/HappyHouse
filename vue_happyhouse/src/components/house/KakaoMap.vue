<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
const houseStore = "houseStore";
const hospitalStore = "hospitalStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markerposiions: [],
      markers: [],
      markers_HP: [],
      geocoder: null,
    };
  },
  methods: {
    removeMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => {
          marker.setMap(null);
        });
      }
      this.markers = [];
    },
    removeMarker_HP() {
      if (this.markers_HP.length > 0) {
        this.markers_HP.forEach((marker) => {
          marker.setMap(null);
        });
      }
      this.markers_HP = [];
    },
    moveMap(dongCode) {
      this.removeMarker();
      let name = "";
      this.dongs.forEach((temp) => {
        if (dongCode == temp.value) {
          name = temp.text;
        }
      });
      this.geocoder.addressSearch(name, (result, status) => {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          this.map.setCenter(coords);
        }
      });
    },

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.2429372, 131.8624647),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();
    },

    //아파트 마커 표시
    displayMarkers(places) {
      this.removeMarker();
      places.forEach((element) => {
        //console.log("element: ", element);
        var coords = new kakao.maps.LatLng(element.lat, element.lng);
        var markerImageSrc = require("@/assets/icon/apartment2.png");
        var imageSize = new kakao.maps.Size(50, 50),
          imageOptions = {
            spriteOrigin: new kakao.maps.Point(10, 0),
            spriteSize: new kakao.maps.Size(40, 36),
          };
        var markerImage = this.createMarkerImage(
          markerImageSrc,
          imageSize,
          imageOptions,
        );

        let marker = new kakao.maps.Marker({
          //map: this.map,
          position: coords,
          image: markerImage,
          //title: element.apartmentName,
          clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
        });

        this.markers.push(marker);
        //this.map.setCenter(coords);
        marker.setMap(this.map);
        // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
        // var iwContent = '<div style="padding:5px;">Hello World!</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var iwContent = `<div style="width:100%; padding:5px; text-align:left; font-family: NanumSquareRoundB, sans-serif;">
                        <div style="text-align:center"><img src="${markerImageSrc}" width="30" alt="No image"></div>
                        <div style="text-align:center">아파트<br></div>
                        이름: ${element.apartmentName}<br>
                        위치: ${element.dongName} ${element.jibun}<br>
                        건축일: ${element.buildYear}년<br>
                        거래금액: ${element.recentPrice}원<br><br>

                        </div>
                        `;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow),
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow),
        );
      }); //forEach
    },
    // 병원 마커 표시
    displayMarkersHP(places) {
      this.removeMarker_HP();

      places.forEach((element) => {
        //console.log("element", element);
        var coords = new kakao.maps.LatLng(element.YPos, element.XPos);
        var markerImageSrc = require("@/assets/icon/hospital3.png");
        var imageSize = new kakao.maps.Size(50, 50), //22 26
          imageOptions = {
            spriteOrigin: new kakao.maps.Point(10, 0),
            spriteSize: new kakao.maps.Size(40, 36), //36 98
          };
        var markerImage = this.createMarkerImage(
          markerImageSrc,
          imageSize,
          imageOptions,
        );
        let marker = new kakao.maps.Marker({
          //map: this.map,
          position: coords,
          image: markerImage,
          //title: element.yadmNm,
        });
        this.markers_HP.push(marker);
        //this.map.setCenter(coords);
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(this.map);

        // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
        // var iwContent = '<div style="padding:5px;">Hello World!</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var iwContent = `<div style="width:100%; padding:5px; text-align:left; font-family: NanumSquareRoundB, sans-serif;">
                        <div style="text-align:center"><img src="${markerImageSrc}" width="30" alt="No image"></div>
                        <div style="text-align:center">병원<br></div>
                        이름: ${element.yadmNm}<br>
                        위치: ${element.addr}<br>
                        전화번호: ${element.telno}<br>
                        종류: ${element.clCdNm}<br><br>

                        </div>
                        `;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
        });

        //console.log("iwContent");

        //커스텀 오버레이를 생성합니다.
        // var customOverlay = new kakao.maps.CustomOverlay({
        //     map: this.map,
        //     position: coords,
        //     content: iwContent,
        //     yAnchor: 1
        // });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow),
          //function(){customOverlay.setMap(this.map);}
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow),
          //function(){customOverlay.setMap(null);}
        );
      });
    },
    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    //마커이미지의 주소와, 크기, 옵션으로 마커 이미지를 생성하여 리턴하는 함수입니다
    createMarkerImage(src, size, options) {
      var markerImage = new kakao.maps.MarkerImage(src, size, options);
      return markerImage;
    },

    // 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
    //  createMarker(position, image) {
    //   var marker = new kakao.maps.Marker({
    //       position: position,
    //       image: image
    //   });
    //    return marker;
    // },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      /* global kakao */
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8d575b7a18c5ef2a0050be212b9129d2&libraries=services";
      document.head.appendChild(script);
    } else {
      // 이미 로딩됨
      this.initMap();
    }
  },
  computed: {
    ...mapState(houseStore, ["houses", "dongs", "dong"]),
    ...mapState(hospitalStore, ["hospitals", "dongs_HP", "dong_HP"]),
    ...mapGetters(houseStore, ["getHouses", "getDong"]),
    ...mapGetters(hospitalStore, ["getHospitals", "getDong"]),
  },
  watch: {
    houses() {
      if (this.getHouses > 0) {
        this.displayMarkers(this.houses);
      }
    },
    hospitals() {
      //console.log("this.getHospitals: ", this.getHospitals);
      if (this.getHospitals > 0) {
        //console.log("displaymarkershp");
        this.displayMarkersHP(this.hospitals);
      }
    },
    dong: {
      deep: true,
      handler() {
        this.moveMap(this.dong);
      },
    },
  },
};
</script>

<style scoped>
#map {
  display: flex;
  /* display: flex;
  justify-content: center;
  align-items: center;
  width: 50vw;
  height: 50vh; */
  width: 100%;
  height: 800px;
}
</style>
