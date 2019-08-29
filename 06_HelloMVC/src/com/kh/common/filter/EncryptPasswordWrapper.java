package com.kh.common.filter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class EncryptPasswordWrapper extends HttpServletRequestWrapper {
	
	public EncryptPasswordWrapper(HttpServletRequest request) {
		super(request);
	}

	// 암호화를 하기 위해 client가 보낸 데이터를 불러오는 
	// getParameter 메소드를 오버라이딩 처리 
	
	@Override
	public String getParameter(String key) {
		String value="";
		if(key!=null && (key.equals("password")||key.equals("cPw")||key.equals("nPw"))) {
			value=getEncryptPw(super.getParameter(key));
			System.out.println(value);
		}else {
			value=super.getParameter(key);
		}
		return value;
	}
	
	private static String getEncryptPw(String pw) {
		// 해쉬알고리즘을 이용함 sha512방식으로 암호화 처리 
		// java api에서 기본적으로 제공하는 암호화처리 객체가 있다. 바로 MessageDigest 
		// 암호화는 비트(바이트)단위로 이루어 짐.
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("SHA-512");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		byte[]bytes;
		try {
			bytes=pw.getBytes("UTF-8");
			md.update(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String encPw=Base64.getEncoder().encodeToString(md.digest());
		return encPw;
	}
}
