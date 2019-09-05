package com.kh.notice.model.service;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;


public class NoticeService {

	private NoticeDao dao=new NoticeDao();
	
	public int selectNoticeCount() {
		Connection conn=getConnection();
		int result=dao.selectNoticeCount(conn);
		close(conn);
		return result;
		
	}
	
	public List<Notice> selectNoticeList(int cPage, int numPerPage){
		Connection conn=getConnection();
		List<Notice> list=dao.selectNoticeList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public Notice selectNotice(int no) {
		Connection conn=getConnection();
		Notice n=dao.selectNotice(conn,no);
		close(conn);
		return n;
	}
	
	public int insertNotice(Notice n) {
		Connection conn=getConnection();
		int result=dao.insertNotice(conn,n);
		if(result>0) {commit(conn);}
		else {rollback(conn);}
		close(conn);
		return result;
	}
	
	public int updateNotice(Notice n) {
		Connection conn=getConnection();
		int result=dao.updateNotice(conn, n);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
}





