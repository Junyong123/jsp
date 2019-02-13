package kr.or.ddit.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

// localhost/profileImg?userId=brown
// localhost/profileImg?userId=userId1
@WebServlet("/profileImg")
public class ProfileImgServlet extends HttpServlet{
	
	private IUserService user;
	
	

	@Override
	public void init() throws ServletException {
		user =new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		resp.setHeader("Content-Disposition", "attachment;filename=profile.png");
//		resp.setContentType("image");
		resp.setContentType("application/octet-stream");
		// 1.사용자 아이디 파라피터 확인
		String userId = req.getParameter("userId");
		
		// 2.해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVO userVO = user.selectUser(userId);
		
		// 3-1.realFilename이 존재할 경우
		// 3-1-1.해당 경로의 파일을 FileInputStream으로 읽는다.
		FileInputStream fis;
		if(userVO != null&&userVO.getRealFilename() != null){
			fis = new FileInputStream(new File(userVO.getRealFilename()));
		}
		
		// 3-2 realFilename이 존재하지 않을 경우
		// 3-2-1./upload/noimg.png (application.getRealPath())
		else{
			ServletContext application = getServletContext(); // application 객체 생성
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		// 4.FileInputStream을 response객체의 outputStream객체를 write
		ServletOutputStream sos  = resp.getOutputStream();
		byte[] buff = new byte[512]; // read하기 위한 byte 필요
		int len = 0;
		
		while((len  = fis.read(buff)) > -1){ // 파일이 없으면 -1이 됨
			sos.write(buff);
		}
		sos.close(); // stream은 종료를 해줘야 한다
		fis.close();
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
