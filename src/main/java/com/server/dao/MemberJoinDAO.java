package com.server.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface MemberJoinDAO {
		// 신규 회원 등록 삽입
		public int insert(
				@Param("userid")String user_id, 
				@Param("userpw")String user_pw, 
				@Param("username")int user_name, 
				@Param("email")int user_email, 
				@Param("zipcode")String user_zipcode, 
				@Param("address1")String user_address1, 
				@Param("address2")String user_address2);
}