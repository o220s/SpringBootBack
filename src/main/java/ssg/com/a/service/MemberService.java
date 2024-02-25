package ssg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.MemberDao;
import ssg.com.a.dto.MemberDto;


@Service
@Transactional
public class MemberService {

	@Autowired
	MemberDao dao;
	
	
	public boolean idCheck(String id) {
		return dao.idCheck(id)>0?true:false;
	}
	
	public int Regi(MemberDto mem) {
		return dao.Regi(mem);
	}
	
	public MemberDto Login(MemberDto mem) {
		return dao.Login(mem);
	}
	
	
}
