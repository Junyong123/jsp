package kr.or.ddit.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.PartUtil;

@WebServlet("/userForm")
@MultipartConfig(maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1.사용자 아이디 중복체크
	      //2-1.중복체크 통과 : 사용자 정보를 db에 입력
	      //2-1-1.사용자 페이징 리스트 1 페이지로 이동
	      String userId = request.getParameter("userId");
	      UserVO duplicateUserVO = userSer.selectUser(userId);
	      
	      if(duplicateUserVO == null){
	         String userNm = request.getParameter("userNm");
	         String alias = request.getParameter("alias");
	         String addr1 = request.getParameter("addr1");
	         String addr2 = request.getParameter("addr2");
	         String zipcode = request.getParameter("zipcode");
	         String pass = request.getParameter("pass");
	         UserVO userVO = new UserVO(userId, userNm, pass, alias, addr1, addr2, zipcode);
	         
	         // 사용자 사진 추가
	         Part profilePart = request.getPart("profile"); // Part를 받아옴
	         String filename = "";
	         String realFilename = "";
	         
	         // 사용자가 사진을 올린 경우
	         if(profilePart.getSize()>0){
	        	 // 1.사용자 테이블에 파일명을 저장(실제 업로드 파일명(fileName), 파일 충돌을 방지하기 위해 사용한 uuid(realFileName))
	        	 String contetDisposition = profilePart.getHeader("Content-Disposition"); // 헤더 파일을 받아오는 방법
	        	 filename = PartUtil.getFileNameFromPart(contetDisposition);
	        	 realFilename = "d:\\picture\\" +UUID.randomUUID().toString();
	        	 
	        	 // 2.디스크에 기록 ( d:\picture\ + reqlFileName )
	        	 profilePart.write(realFilename);
	         }
	         // 올리지 않은 경우에는 작성 필요 x -> 위에서 기본적으로 "" 로 초기화 하였기 때문에
	         
	         
	         userVO.setFilename(filename);
	         userVO.setRealFilename(realFilename);
	         
	         duplicateUserVO = new UserVO(userId, userNm, pass, alias, addr1, addr2, zipcode);
	         int result = userSer.insertUser(duplicateUserVO);
	         
	         //정상입력(성공)
	         if(result == 1){
	            //db에서 데이터를 조작하는 로직을 처리할때는 forward가 아니라 redirect를 사용해야함(새로고침시 최초요청 url로 다시 이동하기때문에)
	            //redirect는 ContextPath를 써줘야하며 redirect는 get방식임
	            //request.getRequestDispatcher("/userPagingList").forward(request, response);;
	            request.getSession().setAttribute("msg", "정상 등록 되었습니다.");
	            response.sendRedirect(request.getContextPath()+"/userPagingList");
	         }
	         //정상입력(실패)
	         else{
	            doGet(request, response);
	         }
	      }
	      //2-2.중복체크 통과 실패 : 사용자 등록페이지로 이동
	      else{
	         //forward 시에는 최초 요청한 method를 변경불가
	         //post --> post
	         //request.getRequestDispatcher("/user/userForm.jsp").forward(request, response);
	         request.setAttribute("msg", "중복체크에 실패했습니다!");
	         doGet(request, response);
	      }
 	}
}
