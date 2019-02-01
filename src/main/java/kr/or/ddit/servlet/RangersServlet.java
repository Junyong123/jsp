package kr.or.ddit.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.rangers.model.RangerVO;
import kr.or.ddit.rangers.service.RangersService;

@WebServlet("/rangersList")
public class RangersServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// service 객체를 이용하여 rangerList를 조회
		RangersService rangersService = new RangersService();
		List<String> rangersList = rangersService.getAll();
		List<RangerVO> rangersVoList = rangersService.getRangerVoAll();
		
		// 해당 데이터를 request 영역에 설정
		request.setAttribute("rangersList", rangersList);
		request.getSession().setAttribute("rangersVoList", rangersVoList); // 세션에 담는다
		
		// 서로 다른 영역에 같은 이름의 속성을 정의 
		request.setAttribute("userName", "brown_request"); // request attribute
		request.getSession().setAttribute("userName", "brown_session"); // session attribute
		
		// application attribute -> servletContext
		ServletContext application = getServletContext();
		application.setAttribute("userName", "brown_application");
		
		// /rangers/rangersList.jsp로 forward
		// rangersList.jsp에서는 해당 데이터를 출력
		request.getRequestDispatcher("/rangers/rangersList.jsp").forward(request, response);
	}
}