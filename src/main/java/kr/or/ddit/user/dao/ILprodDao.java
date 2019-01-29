package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.LprodVO;

public interface ILprodDao {
	LprodVO selectprod(String str);
	
	List<LprodVO> allprod();
	
}
