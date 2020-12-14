package com.server.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.server.dto.UserDTO;

@Repository
public class MemberJoinDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.server.dao.memberMapper.";
	
	// 회원 가입 insert
	public int insertMember(UserDTO userdto) throws SQLException {
		return sqlSession.insert(NAMESPACE + "memberInsert", userdto);
	}
	
	// 아이디 중복 검사
	public int idChk(String user_id) throws SQLException {
		return sqlSession.selectOne(NAMESPACE + "idChk", user_id);
	}
	
	// 기본 회원 정보 수정 위해 검색
	public UserDTO selectMember(String user_id) throws SQLException {
		return sqlSession.selectOne(NAMESPACE + "memberSelect", user_id);
	}
	
	// 기본 회원 정보 수정 Update
	public int updateMember(UserDTO userdto) throws SQLException {
		return sqlSession.update(NAMESPACE + "memberUpdate", userdto);
	}
	
	
	
	// 회원정보 삭제
	public int deleteMember(String user_id) throws SQLException {
		return sqlSession.delete(NAMESPACE + "memberDelete", user_id);
	}

} // end class