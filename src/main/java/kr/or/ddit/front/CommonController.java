package kr.or.ddit.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonController {
	
	
	/**
	* Method : process
	* 작성자 : pc15
	* 변경이력 :
	* @param request
	* @param response
	* @return view 정부
	* Method 설명 : 컨트롤로 실행
	*/
	public String process(HttpServletRequest request,HttpServletResponse response ); 
	
	
}
