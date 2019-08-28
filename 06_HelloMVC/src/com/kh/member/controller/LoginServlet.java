package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		
		//로그인 저장 관리하기 
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		
		
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
			
			//session 속성 값 설정! 
			//setMaxInactiveInterval() : session 생존주기 관리 
			// * 매개변수의 second 만큼만 session을 유지 
			// getCreationTime() : 생성된 시간 
			// getLastAccessedTime() : 마지막 요청 시간 
			// invalidate() : 세션 종료 
			
//			session.setMaxInactiveInterval(60); //세션을 (초단위) 로 유지시켜줌 
			
			// 아이디 cookie를 이요애서 저장하기! 
			// Cookie는 클라이언트 측에서 저장하는 데이터를 말하고
			// 서블릿에서 cookie 객체를 가지고 있음. 
			
			if(saveId != null) {
				Cookie cookie=new Cookie("saveId",id);
				cookie.setMaxAge(3*24*60*60);// 초단위로 계산 (3일 24시간 60분 60초)
				response.addCookie(cookie);
			}else {
				Cookie cookie=new Cookie("saveId",id);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			
			
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
