package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class FileUploadServlet
 */

// 5MB = 5*1024*1024
@WebServlet("/fileUpload")
@MultipartConfig(maxFileSize = 5*1024*1024 ,maxRequestSize=5*5*1024*1024)
public class FileUploadServlet extends HttpServlet {
	
	private static final String Upload_Path = "d:\\picture\\";
	
	private Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/fileUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getContentType();
		String userId = request.getParameter("userId");
		String uploadFile = request.getParameter("uploadFile");
		
		// low level -------------- high level
		// trace , debug , info , warn , error
		// 하위 레벨은 상위 레벨을 포함하는 개념
		// 로깅 레벨이 info : logger.info(...), logger.warn(...), logger.error(...)
		// 로깅 레벨이 error : logger.error(...)
		logger.debug("contentype : "+contentType);
		logger.debug("userId : "+userId);
		logger.debug("uploadFile : "+uploadFile);
		
		//part 정보확인
		//req.getPart(name);
		//req.getParts(); // 여러파일을 할때 Collection<part>로 받는다
//		Collection<Part> parts= request.getParts();
//		
//		for(Part part : parts){
//			logger.debug("partName : {}",part.getName());
//			logger.debug("Contetn-disposition : {}",part.getHeaderNames());
//		}
		
		Part uploadFilePart = request.getPart("uploadFile");
		
		String contentDisposition = uploadFilePart.getHeader("Content-Disposition");
		
		logger.debug("contentDisposition : {}",contentDisposition);
		// {} 안에 contentDisposition 들어가 2개면 2개의 {} 필요
		
		// application
		// localhost/upload --> 물리적 경로를 확인
		ServletContext application = getServletContext();
		// upload url에 실제경로를 확인
		String path = application.getRealPath("/upload");
		logger.debug("path: {}", path);
		
		// 첨부파일 파일명
		if(uploadFilePart.getSize()>0){
			String filename = PartUtil.getFileNameFromPart(contentDisposition);
			// 임의의 값을 랜덤으로 생성
			String uuidFilename = UUID.randomUUID().toString();
//			
			uploadFilePart.write(Upload_Path+uuidFilename);
			uploadFilePart.write(path+File.separator+uuidFilename);
			uploadFilePart.delete();
		}
		
		doGet(request, response);
	}

}
