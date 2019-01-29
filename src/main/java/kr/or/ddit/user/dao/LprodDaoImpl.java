package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.LprodVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class LprodDaoImpl implements ILprodDao {
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	public LprodDaoImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	public LprodVO selectprod(String str){
		sqlSession = sqlSessionFactory.openSession();
		
		LprodVO LprodVO = sqlSession.selectOne("lpord.selectprod", str);
		
		sqlSession.close();
		
		return LprodVO;
	}

	@Override
	public List<LprodVO> allprod() {
		sqlSession = sqlSessionFactory.openSession();
		
		List<LprodVO> prodlist = sqlSession.selectList("lpord.allprod");
		
		return prodlist;
	}

}
