<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax수업</title>
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>Spring에서의 AJAX 사용법</h1>
	
	<h3>1. 요청 시 값 전달, 응답 결과 받아보기</h3>
	이름: <input type="text" id="name"> <br>
	나이: <input type="number" id="age"> <br>
	
	<!-- <button id="btn">전송</button> -->
	<button onclick="test1();">전송</button>
	
	<div id="result1"></div>
	
	<script>
		/* $("#btn").click(function(){
			
		}) */
		
		/* function test1(){
			$.ajax({
				url:"ajax1.do",
				data:{
					name: $("#name").val(),
					age: $("#age").val()
				},
				success:function(result){
					console.log(result);
					$("#result1").html(result);
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			});
		} */
		
		/* JSONArray 사용
		function test1(){
			$.ajax({
				url:"ajax1.do",
				data:{
					name: $("#name").val(),
					age: $("#age").val()
				},
				success:function(result){
					console.log(result);
					
					// 응답데이터가 배열의 형태일 경우 => 인덱스에 접근 가능 result[인덱스]
					let value = "이름: " + result[0] + " <br>나이: " + result[1];
					$("#result1").html(value);
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			});
		}
		*/
		
		function test1(){
			$.ajax({
				url:"ajax1.do",
				data:{
					name: $("#name").val(),
					age: $("#age").val()
				},
				success:function(result){
					console.log(result);
					
					// 응답데이터가 단순객체의 형태일 경우 => 속성에 접근 가능 .속성명
					let value = "이름: " + result.name + "<br>나이: " + result.age;
					$("#result1").html(value);
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			});
		}
		
	</script>

</body>
</html>