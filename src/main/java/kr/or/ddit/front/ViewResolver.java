package kr.or.ddit.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	/**
	* Method : viewResolve
	* 작성자 : pc15
	* 변경이력 :
	* @param viewInfo
	* Method 설명 : viewInfo를 바탕으로 view처리 대상으로 위임
	* 처리 대상 : forward, redirect
	*/
	public void viewResolve(HttpServletRequest req,HttpServletResponse resp,String viewInfo){
		if(viewInfo.startsWith("forward:")){
			//unhappy.subString(2) == happy
			//forward:/front/userList.jsp
			String uri = viewInfo.substring("forward:".length());
			
			try {
				req.getRequestDispatcher(uri).forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(viewInfo.startsWith("redirect:")){
			
		}else{
			//error
		}
	}
}
