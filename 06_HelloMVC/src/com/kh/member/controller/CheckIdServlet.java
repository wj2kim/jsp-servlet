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
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/checkIdDuplicate")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId=request.getParameter("userId");
		System.out.println(userId);
		
		boolean unable=new MemberService().selectOne(userId)!=null?true:false;
		request.setAttribute("unable", unable);
		
		System.out.println(unable);
		
		// view 선택 
		RequestDispatcher rd=request.getRequestDispatcher("/views/member/checkId.jsp");
		rd.forward(request, response);
		
		String selectId=request.getParameter("selectId");
		
		
		
//		MemberService service=new MemberService();
//		Boolean result=service.checkId(userId);
//		System.out.println(result);
		
		//view 선택! 
//		String msg="";
//		String loc="/";
//		String view="";
//		
//		if(result==true) {
//			msg="사용하실 수 없습니다!";
//			view="/views/common/msg.jsp";
//			request.setAttribute("msg", msg);
//			request.setAttribute("loc", loc);
//			RequestDispatcher rd=request.getRequestDispatcher(view);
//			rd.forward(request, response);
//			
//		}else {
//			msg="사용 가능합니다!";
//			view="/views/common/msg.jsp";
//			request.setAttribute("msg", msg);
//			request.setAttribute("loc", loc);
//			RequestDispatcher rd=request.getRequestDispatcher(view);
//			rd.forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
