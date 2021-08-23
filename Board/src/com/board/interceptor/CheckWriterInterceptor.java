package com.board.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.board.beans.ContentBean;
import com.board.beans.UserBean;
import com.board.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {

	@Resource(name= "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@Autowired
	private BoardService boardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String res1 = request.getParameter("contentIdx");
		int contentIdx = Integer.parseInt(res1);
		
		ContentBean currentContentBean = boardService.getContentInfo(contentIdx);
		
		if (currentContentBean.getContentWriterIdx() != loginUserBean.getUserIdx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath +"/board/not_writer");
			
			return false;
		}
		
		return true;
	}
}
