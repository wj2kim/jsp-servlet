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
@WebServlet(name="LoginServlet",urlPatterns = "/login")
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
		//client가 보낸 값을 받아옴!
		String id=request.getParameter("userId");
		String pw=request.getParameter("password");
		
		//로그인 저장관리하기
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
			
		//비지니스 로직수행
		//id와pw를 가지고 가서 DB에 일치하는 값이 있는지
		//확인하고 결과를 가져옴 
		MemberService service=new MemberService();
		Member m=service.selectId(id,pw);
		
		//view에서 보여줄 메세지
		String msg="";
		//view에서 다른 뷰로 이동하는 주소
		String loc="/";//index.jsp
		String view="";
		//기준!
		if(m!=null) {
			//로그인이 되면 session에 로그인결과에 대한
			//값을 유지! http통신 web stateless(상태유지안함)
			//특정값을 유지되는 곳에 저장을 하고 확인
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", m);
			//session 속성값 설정!
			//setMaxInactiveInterval() : session생존주기 관리
			// * 매개변수의 second만큼만 session을 유지
			//getCreationTime() : 생성된 시간
			//getLastAccessedTime() : 마지막 요청시간
			//invalidate() : 세션종료
			//session.setMaxInactiveInterval(60);//세션을 일정기간 후 삭제
			
			//아이디 Cookie를 이용해서 저장하기!
			//Cookie는 클라이언트측에서 저장하는 테이터를 말하고
			//서블릿에서 Cookie객체를 가지고 있음.
			if(saveId != null) {
				Cookie c=new Cookie("saveId",id);
				c.setMaxAge(3*24*60*60);//초단위계산
				response.addCookie(c);				
			}else {
				Cookie c=new Cookie("saveId",id);
				c.setMaxAge(0);
				response.addCookie(c);
			}
			
			
			view="/";
			
			response.sendRedirect(request.getContextPath());
			
		}else {
			//로그인이 안됨!
			msg="아이디나 패스워드가 일치하지 않습니다.!";
			view="/views/common/msg.jsp";
			request.setAttribute("msg",msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rs=request.getRequestDispatcher(view);
			rs.forward(request,response);
		}
//		request.setAttribute("loginMember",m);
		
		
		
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
