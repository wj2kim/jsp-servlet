package com.kh.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no=Integer.parseInt(request.getParameter("no"));
		
		int cPage;
		try {
		cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		//쿠키값 확인하기 
		Cookie[] cookies=request.getCookies();
		String boardCookieVal="";
		boolean hasRead=false; // 읽었는지 안읽었는지 구분하는 기준 
		
		if(cookies!=null) {
			for(Cookie c: cookies) {
				String name=c.getName(); // key 값
				String value=c.getValue();
				if("boardCookie".equals(name));
					boardCookieVal=value; //이전 값 보관 
					if(value.contains("|"+no+"|")) {
						hasRead=true;
						break;
					}
				}
			}
		if(!hasRead) {  // hasRead가 false 면 안 읽었다는 뜻이니 조회수를 추가하고 cookie에 boardNo를 기록 
			Cookie c=new Cookie("boardCookie",boardCookieVal+"|"+no+"|");
			c.setMaxAge(-1); // 브라우저가 close 되거나 로그아웃 했을때 삭제 
			response.addCookie(c);
		}
		
		
		
		
		BoardService bs=new BoardService();
		Board b=bs.selectBoard(no,hasRead);
		String photo=b.getBoardOriginalFilename();
//		String encPhoto= URLEncoder.encode(photo, "UTF-8");
		
		request.setAttribute("board", b);
		request.setAttribute("cPage",cPage);
//		request.setAttribute("photo", encPhoto);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
