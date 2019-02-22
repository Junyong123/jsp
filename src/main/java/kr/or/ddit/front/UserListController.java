package kr.or.ddit.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

/**
* UserListController.java
*
* @author pc15
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* pc15 최초 생성
* 사용자 전체리스트 조회 컨트롤러
* </pre>
*/
public class UserListController implements CommonController{
	
	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) {
		
		// 사용자 리스트를 조회
		IUserService userService = new UserServiceImpl();
		List<UserVO> userList = userService.getAllUser();
		
		// 사용자 리스트 정보 속성설정 - 다른 뷰에서도 볼수 있게 하기 위해
		request.setAttribute("userList", userList);
		
		// forward redirect -응답 확장-> json응답, jsp응답 , tiles응답(view reserver)
		return "forward:/front/userList.jsp"; // 우리가 원하는 키워드와 주소를 입력
	}

}
