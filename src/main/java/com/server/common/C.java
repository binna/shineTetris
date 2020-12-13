package com.server.common;

import org.apache.ibatis.session.SqlSession;

// 언제 어느 곳에서 쓸 수 있도록
// MyBatis용 SqlSession!
public class C {
	public static SqlSession sqlSession;
}