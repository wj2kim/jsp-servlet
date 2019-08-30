package com.kh.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.template.JDBCTemplate.*;
import com.kh.member.model.vo.Member;

import common.template.JDBCTemplate;

public class MemberDao {
	
	private Properties prop=new Properties();

	public MemberDao() {
		String path=MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member selectId(Connection conn, String id, String pw) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		
		String sql=prop.getProperty("selectId");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender((rs.getString("gender").charAt(0)));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int memberJoin(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("register");
		
		System.out.println(prop.getProperty("register"));
		System.out.println(m);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
//	public boolean idCheck(Connection conn, String userId) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		boolean result=true;
//		
//		String sql=prop.getProperty("checkId");
//		try {
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1,userId);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				return result;
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//			result=false;
//		}return result;
//	}
	
	public Member selectOne(Connection conn,String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		String sql=prop.getProperty("selectOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender((rs.getString("gender").charAt(0)));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int memberUpdate(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserName());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getHobby());
			pstmt.setString(7, m.getUserId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	public int memberDelete(Connection conn, String id) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int passwordUpdate(Connection conn,String id, String oripw, String pw) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updatePassword");
		System.out.println(id);
		System.out.println(oripw);
		System.out.println(pw);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			pstmt.setString(3, oripw);
			result=pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
