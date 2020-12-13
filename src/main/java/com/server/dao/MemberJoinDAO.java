package com.server.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJoinDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.server.dao.memberMapper.";
	
//	@Override
//	public int insert(String user_id, String user_pw, int user_name, int user_email, String user_zipcode,
//			String user_address1, String user_address2) {
//		MemberJoinDAO dao = C.sqlSession.getMapper(MemberJoinDAO.class);
//	
//		return 0;
//	}
	
	/*
	public List<?> sampleSelectOne(Map<String, Object> dto)throws SQLException{
		return sqlSession.selectOne(NAMESPACE+"selectTest");
	}
	public List<?> sampleSelectList(Map<String, Object> dto)throws SQLException{
		return sqlSession.selectList(NAMESPACE+"selectTest");
	}
	*/
	public int updateMember(Map<String, Object> dto)throws SQLException{
		return sqlSession.update(NAMESPACE+"memberUpdate", dto);
	}
	
	public int insertMember(Map<String, Object> dto)throws SQLException{
		return sqlSession.insert(NAMESPACE+"memberInsert", dto);
	}
	
	public int deleteMember(Map<String, Object> dto)throws SQLException{
		return sqlSession.delete(NAMESPACE+"memberDelete", dto);
	}
	





}