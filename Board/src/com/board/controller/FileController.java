package com.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PropertySource("/WEB-INF/properties/option.properties")
public class FileController {

	@Value("${path.upload}")
	private String pathUpload;
	
	@RequestMapping(value="/file_download")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("file");
		
		File file = new File(pathUpload + "/" + fileName);
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			sos = response.getOutputStream();
			
			String reFileName = "";
			
			boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE") != -1 ||
					request.getHeader("user-agent").indexOf("Trident") != -1;
				
				if (isMSIE) {
					reFileName = URLEncoder.encode("첨부 파일.jpg", "utf-8");
					reFileName = reFileName.replaceAll("\\+", "%20");
				} else {
					reFileName = new String("첨부 파일.jpg".getBytes("utf-8"), "ISO-8859-1");
				}
				
				response.setContentType("application/octet-stream;charset=utf-8");
				response.addHeader("Content-Disposition", "attachment;fileName=\""+reFileName+"\"");
				response.setContentLength((int)file.length());
				
				int read = 0;
				while ((read = bis.read()) != -1) {
					sos.write(read);
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sos.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
