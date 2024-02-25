package ssg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {

	
	int idCheck(String id);
	
	int Regi(MemberDto mem);
	
	MemberDto Login(MemberDto mem);
	
	
	
}
