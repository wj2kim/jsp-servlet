<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.kh.board.model.vo.Board" %>
<%@ page import ="java.net.URLDecoder" %>
<%
	
	Board b =(Board)request.getAttribute("board");
	String userId=(String)b.getBoardWriter();
	String photo=(String)b.getBoardRenamedFilename();
	
	/* String photo=(String)request.getAttribute("photo"); */
	/* String enPhoto = URLDecoder.decode(photo, "UTF-8"); */
	

%>
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>
	<section>
	<div id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%=b.getBoardNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				<% if (photo!=null) { %>
					<img src="<%=request.getContextPath()%>/upload/board/<%=photo %>" style="max-width:350px; height:300px;">
					<h4><%=photo %></h4>
					<% } %>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%=b.getBoardContent() %></td>
			</tr>
				<th colspan="2">
					<input type="button" value="목록으로" onclick="fn_listBoard();">
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			<% if(loginMember.getUserId().equals(userId)||loginMember.getUserId().equals("admin")) { %>
					<input type="button" value="수정하기" onclick="fn_updateBoard()">
					<input type="button" value="삭제하기" onclick="fn_deleteBoard()">
			<%} %>
				</th>
			</tr>

		</table>

    <script>
	function fn_listBoard(){
		location.href="<%=request.getContextPath()%>/board/boardList?cPage=<%=request.getAttribute("cPage")%>"
	}
    function fn_updateBoard(){
        
    }
    function fn_deleteBoard(){
       
    }
    </script>

    </div>
	
	</section>
<%@ include file="/views/common/footer.jsp"%>