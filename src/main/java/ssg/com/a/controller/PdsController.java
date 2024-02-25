package ssg.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;
import util.PdsUtil;


@RestController
public class PdsController {

	@Autowired
	PdsService service;
	
	@Autowired
	ServletContext servletContext;
	
	@GetMapping("pdslist")
	public List<PdsDto> pdslist(){
		System.out.println("PdsController pdslist " + new Date());
		return service.pdslist();
	}
	
	@PostMapping("pdsupload")
	public String pdsupload(PdsDto pds, 
							@RequestParam(value = "uploadfile", required = false)
							MultipartFile uploadfile,
							HttpServletRequest request) {
		System.out.println("PdsController pdsupload " + new Date());
		
		// 파일업로드 경로
		String path = request.getServletContext().getRealPath("/upload");
		//	String path = "d:\tmp";
			
		String filename = uploadfile.getOriginalFilename();
		
		// 파일명을 변경!
		String newfilename = PdsUtil.getNewFileName(filename);
		
		String filepath = path + "/" + newfilename;
		System.out.println(filepath);
				
		File file = new File(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
						
			os.write(uploadfile.getBytes());	// 실제 업로드 되는 부분
			os.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		
		// db에 접속하기 전 filename, newfilename 저장
		pds.setFilename(filename);
		pds.setNewfilename(newfilename);
		System.out.println(pds.toString());
		
		boolean b = service.pdsupdate(pds);
		if(!b) {
			return "NO";
		}
		
		return "YES";
	}
	
	@GetMapping("getPds")
	public PdsDto getPds(int seq) {
		System.out.println("PdsController getPds() " + new Date());
		
		service.readcount(seq);
		
		return service.getPds(seq);		
	}
	
	@GetMapping("filedownload")
	public ResponseEntity<InputStreamResource> filedownload(int seq, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("PdsController filedownload " + new Date());
		
		PdsDto pds = service.getPds(seq);
		
		// 경로
		String path = request.getServletContext().getRealPath("/upload");
		// String path = "d:\tmp";
		
		// 파일의 타입을 조사
		MediaType mediaType = PdsUtil.getMediaTypeForFileName(servletContext, pds.getFilename());
		System.out.println("filename:" + pds.getFilename());
		System.out.println("mediaType:" + mediaType);
		
		File file = new File(path + File.separator + pds.getNewfilename());
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		// db download count를 증가
		service.downcount(seq);
		
		// 한글 파일의 경우 적용(없으면 파일명이 정상적으로 나오지 않음)
		String filename = URLEncoder.encode(pds.getFilename(), "utf-8");
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"") // 원본파일명
				.contentType(mediaType)
				.contentLength(file.length()).body(is);
	}
	

	
}
