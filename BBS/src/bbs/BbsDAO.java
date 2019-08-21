package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/BBS";
			String dbID="root";
			String dbPassword="2472";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		String SQL="SELECT NOW()";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //  이건 데이터베이스 오류 리턴
	}
	
	public int getNext() {
		String SQL="SELECT bbsID from BBS order by bbsID desc";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1; //마지막 게시물 번호 +1
			}
			return 1; //첫 번째 게시물인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //  이건 데이터베이스 오류 리턴
	}
	
	public int write(String bbsTitle,String userID, String bbsContent) {
		String SQL="INSERT INTO BBS VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //  이건 데이터베이스 오류 리턴
	}
	
	//  BBS 페이지에 출력해주는 메소드
	public ArrayList<Bbs> getList(int pageNumber){
		String SQL="SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable=1 order by bbsID desc limit 10";
		ArrayList<Bbs>list=new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*10);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber) {
		// 페이징 처리 메소드
		String SQL="SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable=1 order by bbsID desc limit 10";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*10);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
