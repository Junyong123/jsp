package kr.or.ddit.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/velocity")
public class VelocityServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 데이터 조회 / 입력
		List<String> rangers = new ArrayList<String>();
		
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("moon");
		
		req.setAttribute("rangers", rangers);
		req.getRequestDispatcher("/template/velocity.vm").forward(req, resp);
		
		// forward / redirect
		
	}
}
