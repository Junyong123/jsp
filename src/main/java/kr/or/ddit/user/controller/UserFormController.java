package kr.or.ddit.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

@WebServlet("/userForm")
public class UserFormController extends HttpServlet {
	
	private IUserService userSer;
	
	@Override
	public void init() throws ServletException {
		userSer = new UserServiceImpl();
	}
	
	/**
	* Method : doGet
	* 작성자 : pc15
	* 변경이력 :
	* @param req
	* @param resp
	* @throws ServletException
	* @throws IOException
	* Method 설명 : 사용자 등록 폼
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/user/userForm.jsp").forward(req, resp);
	}
	
	/**
	* Method : doPost
	* 작성자 : pc15
	* 변경이력 :
	* @param req
	* @param resp
	* @throws ServletException
	* @throws IOException
	* Method 설명 : 사용자 정보 등록
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.사용자 아이디 중복체크
		// 2-1.중복체크 통과 : 사용자 정보를 db에 입력
		UserVO uvo = new UserVO();
		
		Date date = new java.util.Date();
		
		uvo.setUserId("test1");
		uvo.setUserNm("테스트");
		uvo.setAlias("별명");
		uvo.setAddr1("대전 중구 대흥로 76");
		uvo.setAddr2("2층 ddit");
		uvo.setZipcode("34942");
		uvo.setPass("testpass");
		uvo.setReg_dt(date);
		
		userSer.insertUser(uvo);
		
		// 2-1-1.사용자 페이징 리스트 1페이지로 이동
		// 2-2.중복체크 통과 실패 : 사용자 등록페이지로 이동
 	}
}
