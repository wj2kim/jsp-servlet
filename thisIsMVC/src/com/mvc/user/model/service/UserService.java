package com.mvc.user.model.service;

import static common.template.JDBCTemplate.*;

import java.sql.Connection;

import com.mvc.user.dao.UserDao;
import com.mvc.user.vo.User;

public class UserService {
	
	private UserDao dao=new UserDao();
	
	public int userJoin(User u) {
		Connection conn=getConnection();
		int result=dao.userJoin(conn,u);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
