package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.board.beans.ReplyBean;
import com.board.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@RequestMapping(value="/write", method=RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody ReplyBean replyBean){
		
		try {
			replyService.writeReply(replyBean);
			
			return new ResponseEntity<>("ReplyWriteOk", HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	@RequestMapping(value="/write", method=RequestMethod.POST)
//	public String write(ReplyBean replyBean){
//		
//		replyService.writeReply(replyBean);
//		
//		return "board/Main";
//	}
//	
	
	
	
}
