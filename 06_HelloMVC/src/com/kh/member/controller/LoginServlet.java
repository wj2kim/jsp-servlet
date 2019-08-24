package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//가장먼 클라이언트가 보낸 값을 받아와야 함 
		String id=request.getParameter("userId");
		String pw=request.getParameter("password");
		
		//비지니스 로직 수행
		//id와 pw를 가지고 가서 DB에 일치하는 값이 있는지 확인하고 결과를 가져옴 
		MemberService service=new MemberService();
		Member m=service.selectId(id,pw);
		
		//view에서 보내줄 메세지
		String msg="";
		//view에서 다른 뷰로 이동하는 주소
		String loc="/";
		
		String view="";
		
		if(m!=null) {
			//로그인이 되면 session에 로그인 결과에 대한 값을 저장 시키고 유지시킴! 
			//http통신 web stateless(상태유지안함)
			//특정 값을 유지되는 곳에 저장을 하고 확인
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", m);
//			view="/";
			response.sendRedirect(request.getContextPath());
			
		}else {
			//로그인이 안됬을때
			msg="아이디나 비밀번호가 일치하지 않습니다!";
			view="/views/common/msg.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rd=request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
		
//		request.setAttribute("loginMember", m); 이제 넣어줄 필요가 없음.
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
