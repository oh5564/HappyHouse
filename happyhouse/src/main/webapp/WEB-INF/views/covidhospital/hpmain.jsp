<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>코로나 선별진료소 메뉴 메인화면</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- MenuBar Start -->
	<div class="container">
		<!-- <header id="index_header" class="jumbotron text-center mb-1">
			<img id="logo" src="/img/happyhouse.png">
		     </header>  -->
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded"> <!-- navbar-expand-sm -->
			<ul class="navbar-nav"> <!-- nav nav-tabs-->
				<li class="nav-item"><a class="nav-link" href="${root}">Home</a></li>
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="navbardrop"> 동네 정보 </a>
					<div class="dropdown-menu"> <!-- @ -->
                        <a class="dropdown-item" href="#">APT 매매</a>
                        <a class="dropdown-item" href="#">APT 전월세</a>
                        <a class="dropdown-item" href="#">주택 매매</a>
                        <a class="dropdown-item" href="#">주택 전월세</a>
                        <a class="dropdown-item" href="#">상권 정보</a>
                        <a class="dropdown-item" href="#">환경 정보</a>
                        <a class="dropdown-item" href="${root}/covidhospital/page">코로나 선별진료소</a>
                    </div>
                </li>
 				<li class="nav-item"><a class="nav-link" href="${root}/notice/list">Notice</a></li>
				<li class="nav-item"><a class="nav-link" href="#">News</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
			</ul>
		</nav>
        <!-- MenuBar End -->
        <!-- hpmain start -->
          <div class="container text-center mt-5">
			<div class="col-lg-8 mx-auto">
			     <h2 class="p-3 mb-3 mt-2 shadow bg-light">코로나 선별진료소 조회</h2>
			          <div class="container">
							 <div class="card bg-dark text-white">
							    <button type="submit" id="searchallhp">전체 조회</button>
							 </div>
							 <div>
								 이름으로 검색:
								<input type="text" placeholder="이름" name="hospital" id="hospitalname">
							    <input type="submit" value="검색" id="searchhpbyname">
							</div>
							<div>
							         위치로 검색:
							    <input type="text" placeholder="지역" name="hospital" id="hospitalloc">
							    <input type="submit" value="검색" id="searchhpbyloc">								
							 </div>
							 <br>			  
				       </div>					                  
			  </div>
		  </div>
		  <table class = "table mt-2">
		  	  <colgroup>
					 <col width="100">
					 <col width="150">
					 <col width="150">
					 <col width="120">
					 <col width="120">
				</colgroup>
			  <thead>
			    <tr>
			     <th>병원 이름</th>
			     <th>전화번호</th>
			     <th>호흡기 전담클리닉 여부</th>
			     <th>PCR검사 가능 여부</th>
			     <th>주소</th>
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
		     		     
		  	  $(document).on("click", "#searchallhp", function(){
		  		  console.log("test1");
		  		 $.get("${root}" + "/covidhospital/hospital", 
		  			  	function(data, status){
		  			       $("tbody").empty();
		  			       $.each(data, function(index, vo){
		  			    	  let str = `
		  			    		<tr class="${colorArr[index%3]}">
		  			    		<td>${'${vo.hospitalName}'}</td>
								<td>${'${vo.phone}'}</td>
								<td>${'${vo.isClinic}'}</td>
								<td>${'${vo.isPcr}'}</td>
								<td>${'${vo.address}'}</td>  
		  			    	  `;
		  			    	  $("tbody").append(str);
		  			       });
		  			          displayMarkers(data);
		  		          }, "json"
		  	        );	  
		  	    });		  

		  	  $(document).on("click", "#searchhpbyname", function(){
		  		  console.log("test2");
		  		  $.get("${root}" + "/covidhospital/hospital/name", 
		  				{name: $("#hospitalname").val()},
		  			  	function(data, status){
		  			       $("tbody").empty();
		  			       $.each(data, function(index, vo){
		  			    	  let str = `
		  			    		<tr class="${colorArr[index%3]}">
		  			    		<td>${'${vo.hospitalName}'}</td>
								<td>${'${vo.phone}'}</td>
								<td>${'${vo.isClinic}'}</td>
								<td>${'${vo.isPcr}'}</td>
								<td>${'${vo.address}'}</td>  
		  			    	  `;
		  			    	  $("tbody").append(str);
		  			       });
		  			          displayMarkers(data);
		  		          }, "json"
		  	        );	  
		  	    });		  

		  	  $(document).on("click", "#searchhpbyloc", function(){
		  		  console.log("test3");
		  		  $.get("${root}" + "/covidhospital/hospital/loc", 
		  				{sigungu: $("#hospitalloc").val()},
		  			  	function(data, status){
		  			       $("tbody").empty();
		  			       $.each(data, function(index, vo){
		  			    	  let str = `
		  			    		<tr class="${colorArr[index%3]}">
		  			    		<td>${'${vo.hospitalName}'}</td>
								<td>${'${vo.phone}'}</td>
								<td>${'${vo.isClinic}'}</td>
								<td>${'${vo.isPcr}'}</td>
								<td>${'${vo.address}'}</td>  
		  			    	  `;
		  			    	  $("tbody").append(str);
		  			       });
		  			          displayMarkers(data);
		  		          }, "json"
		  	        );	  
		  	    });		  
		  </script>
	</div>
</body>
</html>