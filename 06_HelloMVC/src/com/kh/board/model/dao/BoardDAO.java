package com.kh.board.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;


public class BoardDAO {
	
	private Properties prop=new Properties();
	
	public BoardDAO() {
		String path=BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int countBoardList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("countBoardList");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}return result;	
	}
	
	public List<Board> selectBoardList(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board>list=new ArrayList();
		String sql=prop.getProperty("selectBoardList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
				b.setBoardData(rs.getDate("board_date"));
				b.setReadCount(rs.getInt("board_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public Board selectBoard(Connection conn, int no) {
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		Board b =null;
		String sql=prop.getProperty("selectBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
				b.setBoardData(rs.getDate("board_date"));
				b.setReadCount(rs.getInt("board_readcount"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFilename());
			pstmt.setString(5, b.getBoardRenamedFilename());
			result=pstmt.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateReadCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int selectBoardNo(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="select seq_board_no.currval from dual";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return result;
	}

}
