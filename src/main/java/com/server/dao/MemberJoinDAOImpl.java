package com.server.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.server.common.C;

@Repository
public class MemberJoinDAOImpl implements MemberJoinDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.server.mapper.MemberJoinDAO";
	
	@Override
	public int insert(String user_id, String user_pw, int user_name, int user_email, String user_zipcode,
			String user_address1, String user_address2) {
		MemberJoinDAO dao = C.sqlSession.getMapper(MemberJoinDAO.class);
	
		return 0;
	}
	
	/*
	public List<?> sampleSelectOne(Map<String, Object> dto)throws SQLException{
		return sqlSession.selectOne(NAMESPACE+"selectTest");
	}
	public List<?> sampleSelectList(Map<String, Object> dto)throws SQLException{
		return sqlSession.selectList(NAMESPACE+"selectTest");
	}
	public Object sampleUpdate(Map<String, Object> dto)throws SQLException{
		return sqlSession.update(NAMESPACE+"selectTest", dto);
	}
	public Object sampleDelete(Map<String, Object> dto)throws SQLException{
		return sqlSession.delete(NAMESPACE+"selectTest", dto);
	}
	*/





}