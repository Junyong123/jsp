package kr.or.ddit.utill;

public class CookieUtil {

	private String cookieString;
	
	public CookieUtil(String cookieString) {
		this.cookieString = cookieString;
	}
	

	/**
	* Method : getCookieValue
	* 작성자 : pc15
	* 변경이력 :
	* @param cookieName
	* @return
	* Method 설명 : 쿠키 이름에 해당하는 쿠키 값을 가지고 온다
	*/
	public String getCookieValue(String cookieName) {
		String[] list = cookieName.split("=");
		
		return list[1];
	}
	
}
