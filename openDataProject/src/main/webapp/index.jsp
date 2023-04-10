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
			})
		})

	</script>
	
	<hr>
	
	<h2>도로 통행속도 통계 조회 서비스</h2>
	<button id="btn2">확인</button>
	<select id="direction">
		<option>상행</option>
		<option>하행</option>
	</select>
	<table>
		<thead>
			<th>날짜</th>
			<th>주</th>
			<th>도로명</th>
			<th>도로ID</th>
			<th>방향</th>
			<th>시작주소</th>
			<th>도착주소</th>
			<th>도로길이</th>
			<th>도로종류</th>
			<th>측정00</th>
			<th>측정04</th>
			<th>측정09</th>
			<th>측정14</th>
			<th>측정19</th>
			<th>측정23</th>
		</thead>
		<tbody></tbody>
	</table>
	
	<script>
	
		$("#btn2").click(function(){
			$.ajax({
				url:"road.do",
				data:{$("#direction").val()},
				success:function(data){
					console.log(data);
				},
				error:function(){
					console.log("ajax 통신실패");
				}
			})
		})
	</script>
</body>
</html>