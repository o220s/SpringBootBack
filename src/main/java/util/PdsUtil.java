package util;

import java.util.Date;

import org.springframework.http.MediaType;

import jakarta.servlet.ServletContext;

public class PdsUtil {

	// abc.txt -> 43534534.txt
	public static String getNewFileName(String filename) {
		String newfilename = "";
		String fpost = "";	// .jpg .txt
		
		if(filename.indexOf('.') >= 0) {	// 확장명이 있음
			fpost = filename.substring(filename.indexOf('.'));	// .txt
			newfilename = new Date().getTime() + fpost;			// 43534534.txt			
		}else {								// -1 확장명이 없음
			newfilename = new Date().getTime() + ".back";
		}
		
		return newfilename;
	}
	
	public static MediaType getMediaTypeForFileName(ServletContext sc, String filename) {
		
		String mimType = sc.getMimeType(filename);
		
		try {
			MediaType mediaType = MediaType.parseMediaType(mimType);
			return mediaType;
		}catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}			
	}
}
