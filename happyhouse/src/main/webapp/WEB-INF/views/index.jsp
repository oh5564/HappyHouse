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

		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
					<div class="form-group form-inline justify-content-center">
						<label class="mr-2" for="sido">시도 : </label> <select
							class="form-control" id="sido">
							<option value="0">선택</option>
						</select> <label class="mr-2 ml-3" for="gugun">구군 : </label> <select
							class="form-control" id="gugun">
							<option value="0">선택</option>
						</select> <label class="mr-2 ml-3" for="dong">읍면동 : </label> <select
							class="form-control" id="dong">
							<option value="0">선택</option>
						</select>
						<!-- <button type="button" id="aptSearchBtn">검색</button> -->
					</div>
					<div>
						아파트별 검색:
						<input type="text" placeholder="아파트명" required="required" name="apt" id="aptname">
						<input type="submit" value="검색" id="searchApt">
					</div>
					<div>
					최대 구매가능 주택 정보 조회:
						<input type="number" placeholder="자본" required="required" name="max" id="max">
						<input type="submit" value="조회" id="searchMax">
					
					</div>
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="150">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th>건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
					<div id="map" style="width: 100%; height: 500px;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8d575b7a18c5ef2a0050be212b9129d2&libraries=services"></script>
					<script type="text/javascript" src="js/map.js"></script>
					<script type="text/javascript">
				let colorArr = ['table-primary','table-success','table-danger'];
				let aptCode = 0;
				
				$(document).ready(function(){
                    $(".dropdown-toggle").dropdown();
				});
				
				$(document).ready(function(){					
					$.get(root + "/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
				$(document).on("change", "#sido", function() {
					$.get(root + "/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get(root + "/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#dong", function() {
					$.get(root + "/map/apt"
							,{dong: $("#dong").val()}
							,function(data, status){
								$("tbody").empty();
								$.each(data, function(index, vo) {
									let str = `
										<tr class="${colorArr[index%3]}">
										<td>${'${vo.aptCode}'}</td>
										<td>${'${vo.aptName}'}</td>
										<td>${'${vo.sidoName}'} ${'${vo.gugunName}'} ${'${vo.dongName}'} ${'${vo.jibun}'}</td>
										<td>${'${vo.buildYear}'}</td>
										<td>${'${vo.recentPrice}'}</td>
										<td><button id="fvButton" type="button">관심</button></td>
									`;
									$("tbody").append(str);
									aptCode = vo.aptCode;
								});
								displayMarkers(data);
								
							}
							, "json"
							);
				});
				
					$(document).on("click","#searchApt", function() {
						$.get(root + "/map/searchapt",
								{apt: $("#aptname").val()},
								function(data,status){
									//console.log($('#aptname').val());
									//console.log(data);
									$("tbody").empty();
									$.each(data, function(index, vo) {
										let str = `
											<tr class="${colorArr[index%3]}">
											<td>${'${vo.aptCode}'}</td>
											<td>${'${vo.aptName}'}</td>
											<td>${'${vo.sidoName}'} ${'${vo.gugunName}'} ${'${vo.dongName}'} ${'${vo.jibun}'}</td>
											<td>${'${vo.buildYear}'}</td>
											<td>${'${vo.recentPrice}'}</td>
											<td><button id="fvButton" type="button">관심</button></td>
										`;
										$("tbody").append(str);	
										aptCode = vo.aptCode;
								});
									displayMarkers(data);
									
								}, "json"
						
					);
				});
					$(document).on("click","#searchMax", function() {
						$.get(root + "/map/max",
								{max: $("#max").val()},
								function(data,status){
									if(data =="") {
										alert($("#max").val()+'으로는 부족합니다.');
									}
									
									else{
									$("tbody").empty();
									$.each(data, function(index, vo) {
										let str = `
											<tr class="${colorArr[index%3]}">
											<td>${'${vo.aptCode}'}</td>
											<td>${'${vo.aptName}'}</td>
											<td>${'${vo.sidoName}'} ${'${vo.gugunName}'} ${'${vo.dongName}'} ${'${vo.jibun}'}</td>
											<td>${'${vo.buildYear}'}</td>
											<td>${'${vo.recentPrice}'}</td>
											<td><button id="fvButton" type="button">관심</button></td>
										`;
										$("tbody").append(str);	
										aptCode = vo.aptCode;
								});
									displayMarkers(data);
					
									}
								}, "json"
						
					);
				});
					
					//관심매물 등록
					$(document).on("click","#fvButton", function() {
						console.log("aptCode: " + aptCode);
						
						//alert("test");
						if($(this).html() == '관심'){
							//$(this).html('해제');
							//관심리스트 삭제
							//public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteDto favoriteDto)
							fetch("/favorite/addFavorite", {
								method: 'post',
								headers:{
									'content-type': 'application/json'
								},
								body: JSON.stringify({
									fvid:aptCode,
									fvuserid:"lkw1837",
									fvitem:aptCode									
								})						
								
							}).then(res=>{
								console.log("res: " + res.status);
								if(res.status == 204){
									alert("이미 저장했던 매물입니다.");
								}else{
									$(this).html('해제');
								}
							});
						}else{
							$(this).html('관심');
							//관심리스트 추가
							//public ResponseEntity<?> addFavorite(@RequestBody FavoriteDto favoriteDto)
			
							fetch("/favorite/deleteFavorite", {
								method: 'delete',
								headers:{
									'content-type': 'application/json'
								},
								body: JSON.stringify({
									fvid:aptCode,
									fvuserid:"lkw1837",
									fvitem:aptCode									
								})
							});
						}
					
					});
				</script>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
