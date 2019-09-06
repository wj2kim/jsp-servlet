package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;

import common.policy.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardWriteEndServlet
 */
@WebServlet("/board/boardWriteEnd")
public class BoardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg","공지사항작성오류![form:enctype 관리자에게문의하세요]");
			request.setAttribute("loc","/board/boardWrite");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
			return;
		}
		
		String root=getServletContext().getRealPath("/");
		String saveDir=root+"/upload/board";
		
		int maxSize=1024*1024*10; // 
		
		MultipartRequest mr=new MultipartRequest(request, saveDir, maxSize, "UTF-8", 
				new MyFileRenamePolicy()
				);
		
		String title=mr.getParameter("title");
		String writer=mr.getParameter("writer");
		String content=mr.getParameter("content");
		String oriName=mr.getOriginalFileName("up_file");
		String reName=mr.getFilesystemName("up_file");
		
		Board b= new Board();
		b.setBoardTitle(title);
		b.setBoardWriter(writer);
		b.setBoardContent(content);
		b.setBoardOriginalFilename(oriName);
		b.setBoardRenamedFilename(reName);
	
		
		int result=new BoardService().insertBoard(b);
		
		String msg="";
		String view="/views/common/msg.jsp";
		String loc="";
		
		
		if(result>0) {
			msg="게시물이 정상적으로 등록되었습니다.";
			loc="/board/boardView?no="+result; // 작성한 화면을 보여주기 위해	
		}else {
			msg="게시물 등록 실패";
			loc="/board/boardWrite";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
		.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
