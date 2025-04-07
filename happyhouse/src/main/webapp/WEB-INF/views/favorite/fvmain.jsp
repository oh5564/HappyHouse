<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Framework Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <div class="container">
    <div id="map" style="width: 100%; height: 500px; display:none;"></div>
    <script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8d575b7a18c5ef2a0050be212b9129d2&libraries=services"></script>
	<script type="text/javascript" src="../js/map.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		//관심목록
		$.ajax({
			url:'${root}/favorite/listFavorite',  
			data: {fvuserid: 'lkw1837'},
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(favorites) {
				console.log("favorites: " + favorites);
							
				makeList(favorites);
			},
			error:function(xhr, status, error){
				console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			},
			statusCode: {
				500: function() {
					alert("서버에러.");
					// location.href = "/error/e500";
				},
				404: function() {
					alert("페이지없다.");
					// location.href = "/error/e404";
				}
			}	
		});	
		
		function makeList(favorites) {
			$("#favoritelist").empty();
			$(favorites).each(function(index, favorite) {
				
				//======================================================
				//let info = getClosest(favorite);	
				
				var ps = new kakao.maps.services.Places();
				//콜백함수라서 그냥 안받아와짐.....
				//ps.keywordSearch('지하철', placesSearchCB, {x: favorite.lat, y: favorite.lng, radius: 2000});
				const keywordSearch = place => {
					return new Promise((resolve, reject) => {
						ps.keywordSearch(place, function(result, status){
							if(status == kakao.maps.services.Status.OK){
								resolve(result);
							}else{
								reject(status);
							}
						}, {x: favorite.lat, y: favorite.lng, radius: 2000});
					});
				};
				
				let x1 = favorite.lat;
				let y1 = favorite.lng;
					
				// async-await
				(async () => {
					try {
						let min = 100000;
						let name = "";
						
						const result = await keywordSearch('지하철', placesSearchCB, {x: favorite.lat, y: favorite.lng, radius: 2000});
						//console.log(result);
						//최단거리	
						
						for(var i in result){
	                        let x2 = result[i].y; //37
	                        let y2 = result[i].x; //127
	                        //let dist = Math.sqrt(Math.pow((lastlat - favlat),2) + Math.pow((lastlng - favlng),2));
	                 
	                        let radius = 6371; // 지구 반지름(km)
	                        let toRadian = Math.PI / 180;

	                        let deltaLatitude = Math.abs(x1 - x2) * toRadian;
	                        let deltaLongitude = Math.abs(y1 - y2) * toRadian;

	                        let sinDeltaLat = Math.sin(deltaLatitude / 2);
	                        let sinDeltaLng = Math.sin(deltaLongitude / 2);
	                        let squareRoot = Math.sqrt(
	                            sinDeltaLat * sinDeltaLat +
	                            Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

	                        let dist = 2 * radius * Math.asin(squareRoot);
	                        if(min > dist){
	                        	console.log("dist: " + dist);
	                        	min = dist.toFixed(3);
	                        	name = result[i].place_name;
	                        	//console.log(result[i].place_name);
	                        }
	                        //console.log("lastlat: " + lastlat); //37
	    					//console.log("lastlng: " + lastlng); //126
	    					
						}	
						
						let str = `
							<tr id="view_${'${favorite.aptName}'}" class="view" data-id="${'${favorite.aptName}'}">
								<td>${'${index+1}'}</td>
								<td>${'${favorite.aptName}'}</td>
								<td>${'${favorite.jibun}'}</td>
								<td>${'${favorite.buildYear}'}</td> 
								<td>${'${favorite.recentPrice}'}</td>
								<td>${'${name} ${min}km'}</td>
								<td>
									<button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
									<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
								</td>
							</tr>
							`;
							$("#favoritelist").append(str);
			            
					} catch (e) {
						console.log(e);
					}
				})();
				
                //=====================================================

			});//each
		}
	});
	
	
	</script>
	
    <div class="container">
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${root}">Home</a></li>
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" > 동네 정보 </a>
					<div class="dropdown-menu">
                        <a class="dropdown-item" href="#">APT 매매</a>
                        <a class="dropdown-item" href="#">APT 전월세</a>
                        <a class="dropdown-item" href="#">주택 매매</a>
                        <a class="dropdown-item" href="#">주택 전월세</a>
                        <a class="dropdown-item" href="#">상권 정보</a>
                        <a class="dropdown-item" href="#">환경 정보</a>
                        <a class="dropdown-item" href="#">코로나 선별진료소</a>
                    </div>
                </li>
 				<li class="nav-item"><a class="nav-link" href="${root}/notice/list">Notice</a></li>
				<li class="nav-item"><a class="nav-link" href="#">News</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
				<li class="nav-item"><a class="nav-link" href="${root}/favorite/page">Favorite</a></li>
			</ul>
		</nav>
		<div class="col-lg-12 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="pink">관심목록</mark></h2>
			<div class="mb-3 text-right"><button type="button" class="modiBtn btn btn-outline-info" data-toggle="modal" data-target="#userRegModal">등록</button></div>
		  	<table class="table table-hover">
		  		<colgroup>
		            <col width="120"> 
		            <col width="120">
		            <col width="120">
		            <col width="150">
		            <!-- <col width="*"> -->
		            <col width="120">
		            <col width="150">
		            <col width="120">
		        </colgroup>
		    	<thead>
			      	<tr>
			        	<th>번호</th>
						<th>아파트이름</th>
						<th class="text-center">주소</th>
						<th>건축연도</th>
						<th>최근거래금액</th>
						<th>가까운 교통</th>
						<th>수정/삭제</th>
			      	</tr>
			      	
		    	</thead>
		    	<tbody id="favoritelist"></tbody>
			</table>
		</div>
	</div>
	
	</div>
</body>
</html>