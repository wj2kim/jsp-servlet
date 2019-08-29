<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp"%>
	<section id="enroll-container">
		<h2>회원가입</h2>
		<form action="<%=request.getContextPath()%>/memberEnrollEnd" 
		method="post" onsubmit="return enroll_validate();">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" required>
					<input type="button" value="중복검사" onclick="checkIdDuplicate()">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" id="password_" required>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="passwordck" required>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="userName" id="userName" required>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>
					<input type="number" name="age" id="age">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email" name="email" id="email" placeholder="abc@xyz.com">
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>
					<input type="text" name="phone" type="tel" placeholder="(-없이)01012345678"
					id="phone" maxlength="11" required>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="address" id="address">
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="gender" id=gender0 value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id=gender1 value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="헬스">
					<label for="hobby0">헬스</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산">
					<label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서">
					<label for="hobby2">독서</label>
					<input type="checkbox" name="hobby" id="hobby3" value="게임">
					<label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행">
					<label for="hobby4">여행</label>
				</td>
			</tr>
		</table>
		<input type="submit" value="가입">
		<input type="reset" value="취소">
	</form>
	<script>
		function enroll_validate(){
			//정규표현식을 적용해서 유효성 검사 
			var id=$('#userId_').val().trim();
			if(id.length<4){
				alert("아이디는 4글자 이상 입력!");
				$('#userId_').focus();
				return false;
			}
			return true;
		}
		$(function(){
			$("#passwordck").blur(function(){
				var pw=$("#password_").val().trim();
				var pwck=$(this).val().trim();
				if(pw!=pwck){
					alert("패스워드가 일치하지 않습니다.");
					$("#password_").focus();
					$(this).val("");
					$("#password_").val("");
				}
			});
		});
		
		function checkIdDuplicate(){
			var url="<%=request.getContextPath()%>/checkIdDuplicate?userId="+$('#userId_').val();
			var title="checkId";
			var status="left=500px, top=100px, width=300px, height=200px, scrollbars=yes";
			var popup=open(url,title,status);			

		}
	<%-- 	
		$(function(){
			$('#userId_').keyup(function(){
				if($(this).val().trim().length>=4){
					$.ajax({
						url:"<%=request.getContextPath()%>/checkAjaxId",
						data:{userId:$(this).val()},
						success:function(data){
							console.log(date);
						}
					});
				}
			});
		}) --%>
	</script>
	</section>
<%@ include file="/views/common/footer.jsp"%>