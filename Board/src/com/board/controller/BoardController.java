package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.beans.ContentBean;
import com.board.beans.ContentBean;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired	
	private BoardService boardService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(ContentBean contentBean) {
		boolean result = boardService.selectContentList(contentBean);
		System.out.println(result);
		
		return "board/main";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read() {
		return "board/read";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify() {
		return "board/modify";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete() {
		return "redirect:/board/main";
	}

}
