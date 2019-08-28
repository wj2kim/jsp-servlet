package com.kh.member.model.service;

import static common.template.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	private MemberDao dao=new MemberDao();
	
	public Member selectId(String id,String pw) {
		Connection conn=getConnection();
		Member m=dao.selectId(conn,id,pw);
		close(conn);
		return m;
	}
	
	public int memberJoin(Member m) {
		Connection conn=getConnection();
		int result=dao.memberJoin(conn,m);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
//	public boolean checkId(String userId) {
//		Connection conn=getConnection();
//		boolean result=dao.idCheck(conn,userId);
//		close(conn);
//		return result;
//	}
	
	public Member selectOne(String userId) {
		Connection conn=getConnection();
		Member m=dao.selectOne(conn,userId);
		close(conn);
		return m;
	}
	
	public int memberUpdate(Member m) {
		Connection conn=getConnection();
		int result=dao.memberUpdate(conn, m);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
