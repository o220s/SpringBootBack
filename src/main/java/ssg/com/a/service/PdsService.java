package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;

@Service
@Transactional
public class PdsService {

	@Autowired
	PdsDao dao;
	
	public List<PdsDto> pdslist() {
		return dao.pdslist();
	}
	
	public boolean pdsupdate(PdsDto pds) {
		return dao.pdsupload(pds)>0?true:false;
	}
	
	public int readcount(int seq) {
		return dao.readcount(seq);
	}
	
	public int downcount(int seq) {
		return dao.downcount(seq);		
	}
	
	public PdsDto getPds(int seq) {
		return dao.getPds(seq);
	}
}
