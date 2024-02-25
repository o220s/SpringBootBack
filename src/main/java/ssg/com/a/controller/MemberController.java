package ssg.com.a.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;
	

	@PostMapping("idCheck")
	public String idCheck(String id) {
		System.out.println("MemberController idCheck " + new Date());
		
		boolean isS = service.idCheck(id);
		if(isS) {
			return "NO";
		}
		return "YES";
		
	}
	
	@PostMapping("Regi")
	public int Regi(MemberDto mem) {
		System.out.println("MemberController Regi " + new Date());
		
		return service.Regi(mem);
	}
	
	@PostMapping("Login")
	public MemberDto Login(MemberDto mem) {
		System.out.println("MemberController Login " + new Date());
		
		return service.Login(mem);
	}
}
