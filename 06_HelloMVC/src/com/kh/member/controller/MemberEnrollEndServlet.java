package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;


/**
 * Servlet implementation class JoinServlet
 */
@WebServlet(name = "MemberEnrollEndServlet", urlPatterns = { "/memberEnrollEnd" })
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//클라이언트가 보낸 값 받아오기 
		String id=request.getParameter("userId");
		String pw=request.getParameter("password");
		String name=request.getParameter("userName");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		char gender=request.getParameter("gender").charAt(0);
		String hobby=String.join("," , request.getParameterValues("hobby"));
		
		Member m=new Member();
		m.setUserId(id);
		m.setPassword(pw);
		m.setUserName(name);
		m.setAge(age);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setGender(gender);
		m.setHobby(hobby);
		
		
		System.out.println(m);
		
		// 비지니스 로직 수행 (회원가입)
		MemberService service=new MemberService();
		int result=service.memberJoin(m);
		
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
