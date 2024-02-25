package ssg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

@Mapper
@Repository
public interface BbsDao {

	List<BbsDto> bbslist(BbsParam param);
	
	int getallbbs(BbsParam param);
	
	int readcount(int seq);
	
	int bbswrite(BbsDto dto);
	
	BbsDto bbsdetail(int seq);
	
	int bbsupdate(BbsDto bbs);
	
	int bbsdelete(int seq);
	
	void BbsAnswerUpdate(BbsDto dto);
	
	int BbsAnswerInsert(BbsDto dto);
	
	List<BbsComment> commentList(int seq);
	
	int commentWrite(BbsComment com);
	
	
}
