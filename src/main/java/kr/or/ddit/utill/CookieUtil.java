package kr.or.ddit.utill;

public class CookieUtil {

	private String cookieString;
	String[] splitString ;
	
	public CookieUtil(String cookieString) {
		this.cookieString = cookieString;
		splitString = cookieString.split("; ");
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
		
		String cookieValue = "";
		
		for(String cookie :splitString ){
			if( cookieName.equals(cookie.split("=")[0])){
				cookieValue=cookie.split("=")[1];
				break;
			}
		}
		
		return cookieValue;
	}
	
}
