package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.board.service.UserService;

@RestController
public class RestApiController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/checkUserIdExist/{userId}", method=RequestMethod.GET)
	public String checkUserIdExist(@PathVariable String userId) {
		boolean result = userService.checkUserIdExist(userId);
		
		return result + "";
		
	}
}
