package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.user.model.UserVO;


//@WebFilter("/*")
public class LoginCheckFilter implements Filter {

  
	public LoginCheckFilter(){
		
	}
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("로그인 체크 doFilter");
		
		// place your code here
		
		// 전처리
		// 1.session userVO(LoginServlet에서 정상 로그인 처리 되었을 경우 저장한 속성)가
		// 있는지 확인(정상적으로 로그인한 상태인지 확인)
		// 형변환이 필요
		HttpServletRequest hsr = (HttpServletRequest)request;
		HttpServletResponse hsresp = (HttpServletResponse)response;
		
		HttpSession session = hsr.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		// 2-1.정상 로그인 상태인 경우 - > chain.doFilter를 호출
		if(userVO != null)
			chain.doFilter(request,response);
		// 2-2.로그인 상태가 아닌 경우 - > 로그인 화면으로 이동(/login get)
		// *** 모든 페이지에 대해 적용 해야하나??
		// 세션이 필요한 페이지랑, 필요 하지 않은 페이지랑 구분이 필요(무한루프방지)
		// 세션 검증이 필요없는 요청 (대다수는 검증이 필요함)
		// ** login 페이지 화면요청(/login get) session 검증이 필요 하지 않음
		// ** login 요청 처리(/login post) session 검증이 필요 하지 않음
		// ** /js/폴더, /css/* 하위 모든 파일
		// ex) localhost/login
		else{
			String uri = hsr.getRequestURI(); // login
			// 요청 처리
			// /js/CookieUtil.js, /js/jsCookie.js
			if(uri.equals("/login") || uri.endsWith(".js") || uri.endsWith(".css") 
					|| uri.endsWith(".jpg") || uri.endsWith(".jpeg") 
					|| uri.endsWith(".gif") || uri.endsWith(".png"))
				// startWith("") "" 로 시작하는것
				// endWith("") ""로 끝나는것
				// 로그인 ,js ,css로 시작하는것들은 검증이 필요하지 않다
				chain.doFilter(request, response);
			else{
				hsresp.sendRedirect("/login");
			}
		}
		
		// 후처리
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
