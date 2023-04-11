<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- jQuery 라이브러리 -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h2>실시간 대기 오염 정보</h2>
	
	지역:
	<select id="location">
		<option>서울</option>
		<option>부산</option>
		<option>대전</option>
	</select>
	
	<button id="btn1">해당 지역 대기오염정보 확인하기</button>
	
	<table id="result1" border="1" align="center">
		<thead>
			<th>측정소명</th>
			<th>측정일시</th>
			<th>통합대기환경수치</th>
			<th>미세먼지농도</th>
			<th>아황산가스농도</th>
			<th>일산화탄소농도</th>
			<th>이산화질소농도</th>
			<th>오존농도</th>
		</thead>
		<tbody></tbody>
	</table>
	
	<script>

		$("#btn1").click(function(){
			/* json 형식으로 응답받을 때 형식
			$.ajax({
				url: "air.do",
				data:{location:$("#location").val()},
				success:function(data){
					// console.log(data);
					// console.log(data.response.body.items);
					const itemArr = data.response.body.items;
					
					let value ="";
					for(let i in itemArr) {
						// console.log(itemArr[i]);
						
						let item = itemArr[i];
						
						value += "<tr>"
								+ "<td>" + item.stationName + "</td>"
								+ "<td>" + item.dataTime + "</td>"
								+ "<td>" + item.khaiValue + "</td>"
								+ "<td>" + item.pm10Value + "</td>"
								+ "<td>" + item.so2Value + "</td>"
								+ "<td>" + item.coValue + "</td>"
								+ "<td>" + item.no2Value + "</td>"
								+ "<td>" + item.o3Value + "</td>"
								+ "</tr>";
					}
					
					$("#result1 tbody").html(value);
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			}) */
			
			// xml 형식으로 응답받을 때
			$.ajax({
				url: "air.do",
				data:{location:$("#location").val()},
				success:function(data){
					console.log(data);
					// jQuery 탐색 메소드
					// find 메소드: 기준이 되는 하위 요소들 중 특정요소를 찾을 때 사용(html, xml 다 사용가능)
					
					/* console.log(data.find("item")); */ // find 메소드는 제이쿼리 메소드 / 제이쿼리화 시켜야함
					// console.log($(data).find("item"));
					
					// xml 형식의 응답데이터를 받았을 때
					// 1. 응답데이터 안에 실제 데이터가 담겨있는 요소 선택
					
					let itemArr = $(data).find("item");
					
					// 2. 반복문을 통해 실제 데이터가 담긴 요소들에 접근해서 동적으로 요소 만들기
					let value = "";
					itemArr.each(function(i, item){ // i에는 인덱스값, item은 순차적으로 접근한 요소
						// console.log(item);
						// console.log($(item).find("stataionName")); // <station>중구</station>
						// console.log($(item).find("stataionName").text());
						
						value += "<tr>"
								+ "<td>" + $(item).find("stationName").text() + "</td>"
								+ "<td>" + $(item).find("dataTime").text() + "</td>"
								+ "<td>" + $(item).find("khaiValue").text() + "</td>"
								+ "<td>" + $(item).find("pm10Value").text() + "</td>"
								+ "<td>" + $(item).find("coValue").text() + "</td>"
								+ "<td>" + $(item).find("no2Value").text() + "</td>"
								+ "<td>" + $(item).find("so2Value").text() + "</td>"
								+ "<td>" + $(item).find("o3Value").text() + "</td>"
								+ "</tr>"
					})
					
					// 3. 동적으로 만들어낸 요소를 화면에 출력
					$("#result1 tbody").html(value);
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			})
		})

	</script>
	
	<hr>
	
	<h2>기업기본정보 조회 서비스</h2>
	
	<select id="crno">
		<option>1101110028939</option>
	</select>
	<button id="btn2">확인</button>
	
	<table>
		<thead>
			<th>법인등록번호</th>
			<th>법인명</th>
			<th>법인기본주소</th>
		</thead>
		<tbody></tbody>
	</table>
	
	<script>
		$("#btn2").click(function(){
			$.ajax({
				url:"comp.do",
				data:{crno:$("#crno").val()},
				success:function(data) {
					console.log(data);
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			})
		})
	</script>
	
	<hr>
	
	<h2>지진해일대피소 정보</h2>
	<input type="button" value="실행" id="btn3">
	<div id="result3">
		
	</div>
	
	<script>
		$(function(){
			$("#btn3").click(function(){
				$.ajax({
					url:"disaster.do",
					success:function(data){
						// console.log(data)
						// console.log($(data).find("row"));
						
						// jQuery 방식으로 동적으로 table 요소 만들기
						
						let $table = $("<table border='1'></table>");
						let $thead = $("<thead></thead>");
						let headTr = "<tr>"
									+ "<th>일련번호</th>"
									+ "<th>시도명</th>"
									+ "<th>시군구명</th>"
									+ "<th>대피장소명</th>"
									+ "<th>주소</th>"
									+ "<th>수용가능인원(명)</th>"
									+ "<th>해변으로부터 거리</th>"
									+ "<th>대피소분류명</th>"
									+ "</tr>";
									
						// 결합작업
						$thead.html(headTr);
						
						let $tbody = $("<tbody></tbody>");
						let bodyTr = "";
						
						$(data).find("row").each(function(i, row){ // i는 인덱스값, row는 순차적으로 접근한 요소
							//console.log(row);
							// console.log($(row).find("shel_nm").text());
							
							bodyTr += "<tr>"
									+ "<td>" + $(row).find("id").text() + "</td>"
									+ "<td>" + $(row).find("sido_name").text() + "</td>"
									+ "<td>" + $(row).find("sigungu_name").text() + "</td>"
									+ "<td>" + $(row).find("shel_nm").text() + "</td>"
									+ "<td>" + $(row).find("address").text() + "</td>"
									+ "<td>" + $(row).find("shel_av").text() + "</td>"
									+ "<td>" + $(row).find("lenth").text() + "</td>"
									+ "<td>" + $(row).find("shel_div_type").text() + "</td>"
									+ "</tr>";
						});
						
						// 결합
						$tbody.html(bodyTr);
						
						// jQuery 방식으로 만들어진 요소를 자식으로 추가할 때는 이렇게 해야함
						// html() 메소드는 안에 String을 넣어야함		
						// $table.append($thead, $tbody); // a.append(b) => a 안에 b를 추가하겠다.
						// $table.appendTo("#result3"); // a.appendTo(b) => b 안에 a를 추가하겠다.
						
						$table.append($thead, $tbody)
							  .appendTo("#result3");
					},
					error:function(){
						console.log("ajax 통신 실패");
					}
				})
			})
		})
	</script>
	
</body>
</html>