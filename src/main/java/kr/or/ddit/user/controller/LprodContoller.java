package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.LprodVO;
import kr.or.ddit.user.service.ILprodService;
import kr.or.ddit.user.service.LprodServiceImpl;

/**
 * Servlet implementation class LprodContoller
 */
@WebServlet("/lprodList")
public class LprodContoller extends HttpServlet {
	
	ILprodService lprod;
	
	@Override
	public void init() throws ServletException {
		lprod = new LprodServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lprodparam = request.getParameter("id값");
		
		LprodVO lvo = lprod.selectprod(lprodparam);
		
		request.setAttribute("lvo", lvo);
		request.getRequestDispatcher("/user/lprod.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
