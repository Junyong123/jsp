package kr.or.ddit.utill;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.util.PartUtil;

import org.junit.Test;

public class UtilTest {
	
	/**
	* Method : testCokkieStringParsing
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : cookie 문자열 파싱 테스트
	*/
	@Test
	public void testCokkieStringParsing(){
		/***Given***/
		String cookieString = "userId=borwn; remerber=y; test=value";
		CookieUtil cookieUtil = new CookieUtil(cookieString);
		
		/***When***/
		String cookieValue1 = cookieUtil.getCookieValue("y");
		String cookieValue2 = cookieUtil.getCookieValue("borwn");
		String cookieValue3 = cookieUtil.getCookieValue("value");
				
		/***Then***/
		assertEquals("y",cookieValue1);
		assertEquals("brown",cookieValue2);
		assertEquals("value",cookieValue3);

	}
	
	/**
	* Method : testGetFileNameFromPart
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : part의 content-Dispostion 헤더로 부터 filename을 가져온다
	*/
	@Test
	public void testGetFileNameFromPart(){
		/***Given***/
		String contentDisposition = "form-data; name=\"uploadFile\";filename=\"cony.png\"";
		
		/***When***/
		String fileName = PartUtil.getFileNameFromPart(contentDisposition);
		
		/***Then***/
		assertEquals("brown.png", fileName);

	}
}
