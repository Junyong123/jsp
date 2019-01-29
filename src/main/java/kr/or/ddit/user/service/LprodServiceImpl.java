package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.dao.ILprodDao;
import kr.or.ddit.user.dao.LprodDaoImpl;
import kr.or.ddit.user.model.LprodVO;

public class LprodServiceImpl implements ILprodService {
	
	private ILprodDao lpodDao;
	public LprodServiceImpl(){
		lpodDao = new LprodDaoImpl();
	}
	@Override
	public LprodVO selectprod(String str) {
		return lpodDao.selectprod(str);
	}
	@Override
	public List<LprodVO> allprod() {
		return lpodDao.allprod();
	}
	
}