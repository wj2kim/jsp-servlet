<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Login Page</title>

<script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
<link rel="stylesheet" href="../css/loginstyle.css">
<script src="../js/loginscript.js"></script>

</head>
<body>
    <div class="form">
    <div class="head" class="signup">
      <div onclick="changeTab(this)" data-tab="login" class="login-tab">로그인</div>
      <div onclick="changeTab(this)" data-tab="signup" class="signup-tab">회원가입</div>
    </div>
    <div class="body" id="form-body">
      <div class="login">
        <div class="form-row">
          <label for="">이메일</label>
          <input type="text" placeholder="Email">
        </div>
        <div class="form-row">
          <label for="">비밀번호</label>
          <input type="password" placeholder="Password">
        </div>
        <div class="rem-row">
          <input type="checkbox" id="rem">
          <label for="rem">계정 기억하기</label>
        </div>
        <div class="form-row">
          <button>로그인</button>
        </div>
        <div class="footer">
          <div class="social-form fb">
            <i class="fab fa-facebook"></i> 페이스북 로그인 하기
          </div>
          <div class="social-form google">
            <i class="fab fa-google-plus-g"></i> 구글 로그인 하기
          </div>
        </div>
      </div>
      <div class="signup">
      <form action="<%= request.getContextPath()%>/userJoin" method="post">
        <div class="form-row">
          <label for="">이메일</label>
          <input type="text" placeholder="Email" name="email">
        </div>      
        <div class="form-row">
          <label for="">이름</label>
          <input type="text" placeholder="Name" name="name">
        </div>
        <div class="form-row">
          <label for="">비밀번호</label>
          <input type="password" placeholder="Password" name="password">
        </div>
        <div class="form-row">
          <label for="">비밀번호 확인</label>
          <input type="password" placeholder="Password">
        </div>      
        <div class="rem-row">
          <input type="checkbox" id="agr">
          <label for="agr">이용약관을 확인했습니다.</label>
        </div>      
        <div class="form-row">
          <button>가입하기</button>
        </div> 
       </form>      
      </div>
    </div>
  </div>
</body>
</html>