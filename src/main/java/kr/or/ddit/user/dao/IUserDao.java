package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public interface IUserDao {
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	List<UserVO> getAllUser(SqlSession sqlSession);
	
	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	UserVO selectUser(SqlSession sqlSession,String userId);
	
	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 조회
	 */
	List<UserVO> selectUserPagingList(SqlSession sqlSession,PageVO pageVO);
	
	/**
	 * Method : getUserCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수를 조회
	 */
	int getUserCnt(SqlSession sqlSession);
	
	int insertUser(SqlSession sqlSession,UserVO vo);
	
	/**
	* Method : deleteUser
	* 작성자 : pc15
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(SqlSession sqlSession,String userId);
	
	int updateUser(SqlSession sqlSession,UserVO vo);
	
	int encryptPass(SqlSession sqlSession,UserVO vo);
}