package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet(name="changepassword", urlPatterns="/updatePasswordEnd")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cPw=request.getParameter("cPw");
		String nPw=request.getParameter("nPw");
		String userId=request.getParameter("userId");
		
		//cPw가 일치하는지 확인하고 일치하면???
		//패스워드 변경 일치하지 않으면 변경하지 않고 경고!
		int result=new MemberService().updatePassword(userId,cPw,nPw);
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		String script="self.close();opener.location.href="+request.getContextPath()+"/";
		switch(result) {
			case 1 : msg="비밀번호가 성공적으로 변경되었습니다."; loc="/";
					request.setAttribute("script", script);break;
			case 0 : msg="비밀번호 변경실패";loc="/member/updatePassword";break;
			case -1 : msg="현재비밀번호가 일치하지 않습니다.";loc="/member/updatePassword";break;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
		.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
