package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

@Service
@Transactional
public class BbsService {

	@Autowired
	BbsDao dao;
	
	public List<BbsDto> bbslist(BbsParam param) {
		return dao.bbslist(param);
	}

	public int getallbbs(BbsParam param) {
		return dao.getallbbs(param);
	}
	
	public int readcount(int seq) {
		return dao.readcount(seq);
	}
	
	public int bbswrite(BbsDto bbs) {
		return dao.bbswrite(bbs);
	}
	
	public BbsDto bbsdetail(int seq) {
		return dao.bbsdetail(seq);
	}
	
	public boolean bbsupdate(BbsDto bbs) {
		return dao.bbsupdate(bbs)>0?true:false;
	}
	
	public int bbsdelete(int seq) {
		return dao.bbsdelete(seq);
	}
	
	public boolean bbsanswer(BbsDto dto) {
		dao.BbsAnswerUpdate(dto);		
		return dao.BbsAnswerInsert(dto)>0?true:false;
	}
	
	public List<BbsComment> commentList(int seq) {
		return dao.commentList(seq);
	}
	
	public boolean commentWrite(BbsComment com) {
		return dao.commentWrite(com)>0?true:false;
	}
}
