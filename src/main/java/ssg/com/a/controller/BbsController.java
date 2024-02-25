package ssg.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@RestController
public class BbsController {

	@Autowired
	BbsService service;
	
	@GetMapping("bbslist")
	public Map<String, Object> bbslist(BbsParam param){
		System.out.println("BbsController bbslist " + new Date());
		
		//글 목록
		List<BbsDto> list = service.bbslist(param);
		//글의 총수
		int count = service.getallbbs(param);
		//총 페이지수
		int pageBbs = count / 10;
		if((count % 10) > 0) {
			pageBbs = pageBbs + 1;
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bbslist", list);
		map.put("pageBbs", pageBbs);
		
		return map;			
	}
	
	@GetMapping("bbswrite")
	public int bbswrite(BbsDto bbs) {
		System.out.println("BbsController bbswrite " + new Date());
		
		return service.bbswrite(bbs);
	}
	
	@GetMapping("bbsdetail")
	public BbsDto bbsdetail(int seq) {
		System.out.println("BbsController bbsdetail " + new Date());
		
		service.readcount(seq);
		
		return service.bbsdetail(seq);
	}
	
	@GetMapping("bbsupdate")
	public String bbsupdate(BbsDto bbs) {
		System.out.println("BbsController bbsupdate " + new Date());
		
		boolean isS = service.bbsupdate(bbs);
		if(!isS) {
			return "NO";
		}	
		return "YES";
	}
	
	@GetMapping("bbsdelete")
	public int bbsdelete(int seq) {
		System.out.println("BbsController bbsdelete " + new Date());
		
		return service.bbsdelete(seq);	
	}
	
	@GetMapping("bbsanswer")
	public String bbsanswer(BbsDto dto) {
		System.out.println("BbsController bbsanswer " + new Date());
		
		boolean isS = service.bbsanswer(dto);
		if(!isS) {
			return "NO";
		}
		return "YES";
	}
	
	@GetMapping("commentlist")
	public List<BbsComment> commentlist(int seq){
		System.out.println("BbsController commentlist " + new Date());
		
		return service.commentList(seq);
	}
	
	@GetMapping("commentwrite")
	public String commentwrite(BbsComment com) {
		System.out.println("BbsController commentwrite " + new Date());
		
		boolean isS = service.commentWrite(com);
		if(!isS) {
			return "NO";
		}
		return "YES";
	}
} 
