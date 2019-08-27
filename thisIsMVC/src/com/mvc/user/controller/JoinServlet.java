package com.mvc.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.user.model.service.UserService;
import com.mvc.user.vo.User;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/userJoin")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트가 보낸 값 받아오기 
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		
		User u=new User();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		
		System.out.println(u);
		
		// 비지니스 로직 수행 (회원가입)
		UserService service=new UserService();
		int result=service.userJoin(u);
		
		String msg="";
		String loc="/";
		String view="";
		
		if(result>0) {
			msg="회원가입에 성공하셨습니다!";
			
		}else {
			msg="회원가입에 실패하셨습니다!";
		}
		
		view="/views/common/msg.jsp";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher rd=request.getRequestDispatcher(view);
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
