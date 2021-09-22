package com.board.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView (Exception e) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error_common");
		modelAndView.addObject("exception", e);
		
		return modelAndView;
	}
}
