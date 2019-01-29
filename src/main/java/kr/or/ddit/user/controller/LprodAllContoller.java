package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/lprodAllList")
public class LprodAllContoller extends HttpServlet {
	
	ILprodService lprod;
	
	@Override
	public void init() throws ServletException {
		lprod = new LprodServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<LprodVO> lvo = lprod.allprod();
		
		request.setAttribute("lvo", lvo);
		request.getRequestDispatcher("/user/lprodAll.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
