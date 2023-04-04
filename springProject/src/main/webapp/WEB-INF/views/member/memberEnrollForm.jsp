<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 부트스트랩에서 제공하고 있는 스타일 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 부트스트랩에서 제공하고 있는 스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.content {
	background-color: rgb(247, 245, 245);
	width: 80%;
	margin: auto;
}

.innerOuter {
	border: 1px solid lightgray;
	width: 80%;
	margin: auto;
	padding: 5% 15%;
	background: white;
}
</style>
</head>
<body>
	<!-- 
		회원가입 버튼을 누르는 순간 enrollFomr.me 태워서 방금 만든 여기 뜨게 하기 
		회원가입폼 jsp로 변환해서 해당화면 잘 뜨는지 확인	
	-->
	    <!-- 이쪽에 메뉴바 포함 할꺼임 -->
    <jsp:include page="../common/header.jsp"/>
    
    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post" id="enrollForm">
                <div class="form-group">
                    <label for="userId">* ID :</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Please Enter ID" required>

					<div id="checkResult" style="font-size:0.8em; display:none;"></div>
					                    
                    <br>
                    <label for="userPwd">* Password :</label>
                    <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Please Enter Password" required><br>
                    
                    <label for="checkPwd">* Password Check :</label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required><br>
                    
                    <label for="userName">* Name :</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Please Enter Name" required><br>
                    
                    <label for="email"> &nbsp; Email :</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Please Enter Email"><br>
                    
                    <label for="age"> &nbsp; Age :</label>
                    <input type="number" class="form-control" id="age" name="age" placeholder="Please Enter Age"><br>
                    
                    <label for="phone"> &nbsp; Phone :</label>
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Please Enter Phone (-없이)"><br>
                    
                    <label for="address"> &nbsp; Address :</label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="Please Enter Address"><br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>
                    
                </div>
                <br>
                <div class="btns" align="center">
                    <button id="enrollBtn" type="submit" class="btn btn-primary" disabled>회원가입</button>
                    <button type="reset" class="btn btn-danger"> 초기화</button>
                </div>
            </form>
        </div>
        
        <script>
        	$(function(){
        		// 아이디 입력하는 input 요소 객체 변수에 담아두기
        		const $idInput = $("#enrollForm input[name=userId]");
        		
        		$idInput.keyup(function(){
        			// console.log($idInput.val());
        			
        			// 우선 최소 5글자 이상으로 입력되어 있을때만 ajax 요청해서 중복체크 하도록
        			if($idInput.val().length >= 5) {
        				$.ajax({
        					url:"idCheck.me",
        					data:{checkId:$idInput.val()},
        					success:function(result){
        						if(result == "NNNNN"){ // 사용 불가능
        							// 빨간 메시지로 사용불가능
        							$("#checkResult").show();
        							$("#checkResult").css("color", "red").text("아이디가 중복됩니다. 다시 입력하세요.");
        							
        							// 버튼 비활성화
        							$("#enrollForm :submit").attr("disabled", true);
        						}else{// 사용 가능
        							// 초록색 메시지로 사용가능 출력
        							$("#checkResult").show();
        							$("#checkResult").css("color", "green").text("사용 가능한 아이디입니다.");
        							
        							$("#enrollForm :submit").removeAttr("disabled");
        						}
        						
        					},
        					error:function(){
        						console.log("ajax 통신 실패");
        					}
        				})
        			} else{ // 5글자 미만일 경우 => 지울수도 있으니까.. 버튼 비활성화, 메시지 숨기기
        				$("#checkResult").hide();
        				$("#enrollForm :submit").attr("disabled", true);
        				
        			}
        			
        		})
        	})
        </script>
      
        <br><br>
    </div>

    <!-- 이쪽에 푸터바 포함할꺼임 -->
    <jsp:include page="../common/footer.jsp"/>
    
</body>
</html>