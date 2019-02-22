package kr.or.ddit.front;

import java.util.HashMap;
import java.util.Map;

/**
 * HandlerMapper.java
 *
 * @author SEM
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정자 수정내용
 * ------ ------------------------
 * SEM 최초 생성
 * 	HttpServlet을 상속하지 않고 처리하기 위함
 *	uri 정보를 바탕으로 해당 요청을 처리해줄 컨트롤러를 생성, 반환 해주는 객체
 * </pre>
 */
public class HandlerMapper {
	//Map<url 문자열, url 요청을 처리할 컨트롤러 클래스명>
	private Map<String, String> urlControllerMap;
	
	public HandlerMapper(){
		//db, properties 에서 데이터를 조회해야되나
		//테스트 목적으로 직접 넣어준다.
		urlControllerMap = new HashMap<String, String>();
		
		urlControllerMap.put("/userList.do", "kr.or.ddit.front.UserListController");
		urlControllerMap.put("/rangersList.do", "kr.or.ddit.front.RangersListController");	
	}
	
	/**
	 * Method : getController
	 * 작성자 : SEM
	 * 변경이력 :
	 * @param uri
	 * @return
	 * Method 설명 : uri 요청을 처리해줄 pojo controller 리턴
	 */
	public CommonController getController(String uri){
		String classInfo = urlControllerMap.get(uri);
		
		// 인스턴스를 생성하는 방법
		// 1. Integer i = new Integer(); // 객체를 생성하기 위해서 [new] 연산자를 사용 <=지금까지 사용방법
		// 2. String msg = "msg"; // 예외적인 케이스
		
		// 3. class 정보를 이용하여 객체를 생성 : 문자열을 이용-> 클래스를 생성이 가능 <= 새로운 방법
		
//		UserVO uvo = new UserVO(); 이런 형식으로 클래스를 만듬 보통은
		CommonController controller = null;
		
		try {
			Class clazz = Class.forName(classInfo);
			// class는 이미 지정된 단어이기 때문에 비슷한 clazz로 표현
			// 위와 같은 유형 that = this;
			
			// 인터페이스를 이용해 두개의 컨트롤로를 연결해 준다(다형성이용)
			// if문을 사용하지 않아도 됨으로써 코드의 간결화 가능
			controller = (CommonController)clazz.newInstance();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		
		
		return controller;
	}
}















