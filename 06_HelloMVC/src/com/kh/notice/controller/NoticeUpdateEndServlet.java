package com.kh.notice.controller;

import java.io.File;
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
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/notice/updateNoticeEnd")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된 요청입니다.");
			request.setAttribute("loc", "/notice/noticeList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String saveDir=getServletContext().getRealPath("/upload/notice");
		int maxSize=1024*1025*10;
		
		MultipartRequest mr= new MultipartRequest(
				request,
				saveDir,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		String title= mr.getParameter("title");
		String writer= mr.getParameter("writer");
		String content= mr.getParameter("content");
		String fileName= mr.getFilesystemName("up_file");
		String oriFile=mr.getParameter("ori_file"); // 파일 삭제용
		
		File f=mr.getFile("up_file");
		
		if(f!=null && f.length()>0) {
			File deleteFile=new File(saveDir+"/"+oriFile);
			boolean temp=deleteFile.delete();
			System.out.println(temp+"이거 지워야함");
			
		}else {
			fileName=oriFile;
		}
		Notice n=new Notice();
		n.setNoticeTitle(title);
		n.setNoticeWriter(writer);
		n.setNoticeContent(content);
		n.setFilePath(fileName);
		n.setNoticeNo(Integer.parseInt(mr.getParameter("no")));
		
		int result=new NoticeService().updateNotice(n);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="공지사항 수정이 완료되었습니다.";
			loc="/notice/noticeList";
		}else {
			msg="공지사항 수정에 실패하였습니다.";
		}
		loc="/notice/updateNotice?no="+mr.getParameter("no");
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
