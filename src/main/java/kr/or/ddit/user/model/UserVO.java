package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserVO {
	private String userId; // 사용자 아이디
	private String userNm; // 사용자 이름
	private String pass; // 사용자 비밀번호
	private String alias; // 별명
	private String addr1; // 주소
	private String addr2; // 상세주소
	private String zipcode; // 우편번호
	private String filename; 
	private String realFilename;
	private Date reg_dt; // 등록일시

	public UserVO(){
		// 기본 생성자를 생성해주지 않으면 매개변수를 집어넣은 생성자를 작성할 경우 오류가 발생한다. mybatis문제
	}

	public UserVO(String userId, String userNm, String pass, String alias,
			String addr1, String addr2, String zipcode) {
		super();
		this.userId = userId;
		this.userNm = userNm;
		this.pass = pass;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getRealFilename() {
		return realFilename;
	}
	
	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	// reg_dt 값을 yyyy-MM-dd 포맷팅
	public String getReg_dt_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(reg_dt);
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userNm=" + userNm + ", pass="
				+ pass + ", alias=" + alias + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", zipcode=" + zipcode + ", filename=" + filename
				+ ", realFilename=" + realFilename + ", reg_dt=" + reg_dt + "]";
	}

}