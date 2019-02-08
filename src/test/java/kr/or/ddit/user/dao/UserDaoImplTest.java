package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
	private IUserDao userDao;
	private SqlSession sqlSession;
	
	@Before
	public void setup(){
		userDao = new UserDaoImpl();
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
		userDao.deleteUser(sqlSession, "test1");
	}
	
	@After
	public void tearDown(){
		sqlSession.close();
	}

	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVO> list = userDao.getAllUser(sqlSession);
		
		/***Then***/
		assertNotNull(list);
		assertEquals(105, list.size());
		
	}
	
	@Test
	public void testSelectUser(){
		/***Given***/
		
		/***When***/
		UserVO userVO = userDao.selectUser(sqlSession,"brown");
		
		/***Then***/
		assertNotNull(userVO);

	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		List<UserVO> userList = userDao.selectUserPagingList(sqlSession,pageVO);
		for(UserVO vo : userList){
			System.out.println(vo.toString());
		}

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());

	}
	
	@Test
	public void testGetUserCnt(){
		/***Given***/
		
		/***When***/
		int userCnt = userDao.getUserCnt(sqlSession);
		
		/***Then***/
		assertEquals(105, userCnt);
		
	}
	
	@Test
	public void testPagination(){
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
	
		/***When***/
		System.out.println(Math.ceil((userCnt*1.0/pageSize)));
		int lastPage = (int)Math.ceil((userCnt*1.0/pageSize));
		System.out.println(lastPage);
		
		/***Then***/
		assertEquals(11, lastPage);
		
	}
	
	@Test
	public void testInsetUser(){
		/***Given***/
		UserVO uvo = new UserVO();
		int cnt = 0;
		Date date = new java.util.Date();
		
		uvo.setUserId("test1");
		uvo.setUserNm("테스트");
		uvo.setAlias("별명");
		uvo.setAddr1("대전 중구 대흥로 76");
		uvo.setAddr2("2층 ddit");
		uvo.setZipcode("34942");
		uvo.setPass("testpass");
		uvo.setReg_dt(date);
		
		userDao.deleteUser(sqlSession,"test1");
		/***When***/
		cnt = userDao.insertUser(sqlSession,uvo);

		/***Then***/
		assertEquals(1, cnt);
	}
}