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
}
