<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
const hospitalStore = "hospitalStore";
export default {
  name: "HospitalKakaoMap",
  data() {
    return {
      map: null,
      markerposiions: [],
      markers: [],
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

    displayMarkers(places) {
      this.removeMarker();
      places.forEach((element) => {
        //console.log("elements: ", element);
        var coords = new kakao.maps.LatLng(element.YPos, element.XPos);
        let marker = new kakao.maps.Marker({
          map: this.map,
          position: coords,
          title: element.apartmentName,
        });
        this.markers.push(marker);
        this.map.setCenter(coords);
      });
    },
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
    ...mapState(hospitalStore, ["hospitals", "dongs", "dong"]),
    ...mapGetters(hospitalStore, ["getHospitals", "getDong"]),
  },
  watch: {
    hospitals() {
      if (this.getHospitals > 0) {
        this.displayMarkers(this.hospitals);
      }
    },
    dong() {
      this.moveMap(this.dong);
    },
  },
};
</script>

<style scoped>
#map {
  width: 400px;
  height: 500px;
}
</style>
