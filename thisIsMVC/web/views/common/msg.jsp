<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 창</title>
</head>
<body>
	<%
		String msg = (String) request.getAttribute("msg");
		String loc = (String) request.getAttribute("loc");
	%>
	<script>
		alert('<%=msg%>');
		location.href="<%=request.getContextPath()%><%=loc%>";
	</script>
</body>
</html>