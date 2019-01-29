package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.LprodVO;

public interface ILprodService {
	
	LprodVO selectprod(String userId);
	List<LprodVO> allprod();
}