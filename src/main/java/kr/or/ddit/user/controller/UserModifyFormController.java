package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.PartUtil;

@WebServlet("/userModifyForm")
@MultipartConfig(maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class UserModifyFormController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private IUserService userService;
   
   @Override
   public void init() throws ServletException {
      userService = new UserServiceImpl();
   }

   /**
    * Method : doGet
    * 작성자 : pc11
    * 변경이력 :
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    * Method 설명 : 사용자 정보 조회
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userId = request.getParameter("userId");
      UserVO userVO = userService.selectUser(userId);
      
      request.setAttribute("userVO", userVO);
      
      request.getRequestDispatcher("/user/userModifyForm.jsp").forward(request, response);
   }

   /**
    * Method : doPost
    * 작성자 : pc11
    * 변경이력 :
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    * Method 설명 : 사용자 정보 수정
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      String userId = request.getParameter("userId");
      String userNm = request.getParameter("userNm");
      String alias = request.getParameter("alias");
      String addr1 = request.getParameter("addr1");
      String addr2 = request.getParameter("addr2");
      String zipcode = request.getParameter("zipcode");
      String pass = request.getParameter("pass");
      
      Part profilePart = request.getPart("profile");
      String filename = "";
      String realFilename = "";
      
      if(profilePart.getSize()>0){
      // 기존 유지
	      String contetDisposition = profilePart.getHeader("Content-Disposition"); // 헤더 파일을 받아오는 방법
	 	  filename = PartUtil.getFileNameFromPart(contetDisposition);
	 	  realFilename = "d:\\picture\\" +UUID.randomUUID().toString();
	 	 
	 	  // 2.디스크에 기록 ( d:\picture\ + reqlFileName )
	 	  profilePart.write(realFilename);
	 	  profilePart.delete();
      }
      Logger logger = LoggerFactory
			.getLogger(UserModifyFormController.class);
      
      
      
      UserVO userVO = new UserVO(userId, userNm, alias, addr1, addr2, zipcode, pass);
      
      userVO.setFilename(filename);
      userVO.setRealFilename(realFilename);
      
      int result = userService.updateUser(userVO);
      
      logger.debug("******************");
      logger.debug(realFilename);
      logger.debug(filename);
      logger.debug(result+"");
      logger.debug("******************");
      
      if(result == 1){
         response.sendRedirect(request.getContextPath() + "/user?userId=" + userId);
      }else{
         doGet(request, response);
      }
   }

}