<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member"%>
<%
	Member m=(Member)request.getAttribute("member");
	String hobby =m.getHobby();


 	String[] hobbys=new String[5];
	if(hobby!=null){
		for(String h : hobby.split(",")){
			switch(h){
				case "운동":hobbys[0]="checked" ;break;
				case "등산":hobbys[1]="checked" ;break;
				case "독서":hobbys[2]="checked" ;break;
				case "게임":hobbys[3]="checked" ;break;
				case "여행":hobbys[4]="checked" ;break;
			}
		}
	}

%>
<%@ include file="/views/common/header.jsp"%>
	<section id="enroll-container">
	<h2>회원정보수정</h2>
	<form action="<%=request.getContextPath()%>/memberUpdate"
	name="updateMemberFrm" method="post" onsubmit="return update_validate();">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="userId_" value="<%=m.getUserId()%>" name="userId" readonly></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" id="userName" value="<%=m.getUserName()%>" name="userName"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="number" id="age" value="<%=m.getAge()%>" name="age"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" id="email" value="<%=m.getEmail()%>" name="email"></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="tel" id="phone" value="<%=m.getPhone()%>" name="phone"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" id="addresss" value="<%=m.getAddress()%>" name="address"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<%if(m.getGender()=='M'){%>
					<input type="radio" name="gender" id="gender0" value="M" checked disabled><label for="gender">남성</label>
					<input type="radio" name="gender" id="gender1" value="F" disabled><label for="gender">여성</label>
				<%} else {%>
					<input type="radio" name="gender" id="gender0" value="M" disabled><label for="gender">남성</label>
					<input type="radio" name="gender" id="gender1" value="F" checked disabled><label for="gender">여성</label>
				<%} %>
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>

					<!-- case 를 이용하는 방법 -->
					<!-- <input type="checkbox" name="hobby" id="hobby0" value="운동" <%=hobbys[0]%>>
					<label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" <%=hobbys[1]%>>
					<label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" <%=hobbys[2]%>>
					<label for="hobby2">독서</label><br>
					<input type="checkbox" name="hobby" id="hobby3" value="게임" <%=hobbys[3]%>>
					<label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" <%=hobbys[4]%>>
					<label for="hobby4">여행</label> -->


					<!-- contain 을 이용하는 방법 -->
					<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=m.getHobby().contains("운동")?"checked":""%>>
					<label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" <%=m.getHobby().contains("등산")?"checked":""%>>
					<label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" <%=m.getHobby().contains("독서")?"checked":""%>>
					<label for="hobby2">독서</label><br>
					<input type="checkbox" name="hobby" id="hobby3" value="게임" <%=m.getHobby().contains("게임")?"checked":""%>>
					<label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" <%=m.getHobby().contains("여행")?"checked":""%>>
					<label for="hobby4">여행</label>
				</td>
			</tr>
		</table>
		<input type="submit" onclick="fn_updateMember();" value="정보수정">
		<input type="button" onclick="fn_deleteMember();" value="탈퇴하기">
	</form>
	</section>
	<script>
		function fn_updateMember(){
			var name=$("#userName").val();
			var age=$("#age").val();
			var email=$("#email").val();
			var phone=$("#phone").val();
			var address=$("#address").val();
			var hobby=document.getElementsByName(hobby).val();;

			
		}
	</script>
<%@ include file="/views/common/footer.jsp"%>