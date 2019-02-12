package kr.or.ddit.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	
	// 1.logger선언
	
//	private Logger logger =LoggerFactory.getLogger(Class);
	private Logger logger =LoggerFactory.getLogger(LogbackTest.class);
//	== private Logger logger =LoggerFactory.getLogger("kr.or.ddit.util.log.LogbackTest");
	
	public LogbackTest(){
		// logback이 스태틱이기 때문에 main에 선언 불가
		System.out.println("test");
		logger.trace("trace"+"test");
		logger.debug("debug"+"test");
		logger.info("info"+"test");
		logger.warn("warn"+"test");
		logger.error("error"+"test");
		logger.error("error {}, {}, {}"+"test","test2","test3");
	}
	
	public static void main(String[] args) {
		LogbackTest logbackTest = new LogbackTest();
	}
}
