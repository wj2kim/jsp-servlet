package com.kh.board.model.service;
import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;

public class BoardService {
	
	private BoardDAO dao=new BoardDAO();
	
	public int countBoardList() {
		Connection conn=getConnection();
		int result=dao.countBoardList(conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardList(int cPage,int numPerPage) {
		Connection conn=getConnection();
		List<Board> list=dao.selectBoardList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public Board selectBoard(int no, boolean hasRead) {
		Connection conn=getConnection();
		Board b=dao.selectBoard(conn, no);
		if(!hasRead && b!=null) {
			int result=dao.updateReadCount(conn, no);
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}
		close(conn);
		return b;
	}
	
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn,b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	

}
