package com.server.dto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDTO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.server.login.";
	
	public List<?> sampleSelectOne(Map<String, Object> dto)throws SQLException{
		return sqlSession.selectOne(NAMESPACE+"selectTest");
	}
	public List<?> sampleSelectList(Map<String, Object> dto)throws SQLException{
		return sqlSession.selectList(NAMESPACE+"selectTest");
	}
	public Object sampleInsert(Map<String, Object> dto)throws SQLException{
		return sqlSession.insert(NAMESPACE+"selectTest", dto);
	}
	public Object sampleUpdate(Map<String, Object> dto)throws SQLException{
		return sqlSession.update(NAMESPACE+"selectTest", dto);
	}
	public Object sampleDelete(Map<String, Object> dto)throws SQLException{
		return sqlSession.delete(NAMESPACE+"selectTest", dto);
	}
}