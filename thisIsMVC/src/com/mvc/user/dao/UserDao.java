package com.mvc.user.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mvc.user.vo.User;

import common.template.JDBCTemplate;

public class UserDao {
	
	private Properties prop=new Properties();
	
	public UserDao(){
		String path=UserDao.class.getResource("/sql/user/user-query.properties").getPath();
	try {
		prop.load(new FileReader(path));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int userJoin(Connection conn, User u) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("register");
		
		System.out.println(prop.getProperty("register"));
		System.out.println(u);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getEmail());
			pstmt.setString(2, u.getName());
			pstmt.setString(3, u.getPassword());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}

}
