package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet(name="UpdatePasswordEndServlet", urlPatterns ="/updatePasswordEnd")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("userId");
		String oripw=request.getParameter("cPw");
		String pw=request.getParameter("nPw");
		
		MemberService service=new MemberService();
		int result=service.passwordUpdate(id,oripw,pw);
		
		String msg="";
		
		if(result>0) {
			msg="비밀번호 변경이 완료되었습니다!";
			request.setAttribute("closeMsg", "close");
			
		}else {
			msg="현재비밀번호가 맞지 않습니다!";	
			request.setAttribute("loc", "/updatePassword?userId="+ id);
		}
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd=request.getRequestDispatcher("/views/common/msg.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
