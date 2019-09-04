package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/notice/noticeWriteEnd")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//multipart로 전송되었는지 request를 확인
		//ServletFileUpload객체이용
		//isMultipartContent(request) -> multipart인지아닌지 확인가능
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg","공지사항작성오류![form:enctype 관리자에게문의하세요]");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
			return;
		}
	
		//파일업로드 작성진행
		//1. 파일을 저장할 서버의 실제경로(파일시스템상 경로)를 
		//   불러옴
		//2. 파일저장 최대크기를 결정.
		//3. cos.jar에서 지원하는 MultipartRequest객체를 생성
		// 끝!
		
		//저장경로 설정
		String root=getServletContext().getRealPath("/");
		String saveDir=root+"/upload/notice";
		System.out.println(saveDir);
		//String saveDir2=root+File.separator+"upload"+File.separator+"notice";
		//업로드 파일 크기 설정
		int maxSize=1024*1024*10;
		
		//MultipartRequest객체생성
		//객체생성시 매개변수가 있는 생성자를 이용
		//매개변수  
		// 1 : request
		// 2 : 파일저장경로
		// 3 : 파일최대크기
		// 4 : 인코딩값
		// 5 : rename정책(파일이름) 중복 X
		MultipartRequest mr=new MultipartRequest(
							request,
							saveDir,
							maxSize,
							"UTF-8",
							new DefaultFileRenamePolicy()
				);
		String title=mr.getParameter("title");
		String writer=mr.getParameter("writer");
		String content=mr.getParameter("content");
		String fileName=mr.getFilesystemName("up_file");
		System.out.println("title : "+title);
		System.out.println("writer : "+writer);
		System.out.println("content : "+content);
		System.out.println("fileName : "+fileName);
		Notice n=new Notice();
		n.setNoticeTitle(title);
		n.setNoticeWriter(writer);
		n.setNoticeContent(content);
		n.setFilePath(fileName);
		int result=new NoticeService().insertNotice(n);
		
		//view화면선택
		String msg="";
		String view="/views/common/msg.jsp";
		String loc="";
		
		if(result>0) {
			msg="공지사항이 정상적으로 등록되었습니다.";
			loc="/admin/noticeList";
		}else {
			msg="공지사항 등록 실패";
			loc="/notice/noticeWrite";
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
