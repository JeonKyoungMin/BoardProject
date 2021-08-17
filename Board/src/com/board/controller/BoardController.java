package com.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.beans.ContentBean;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired	
	private BoardService boardService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(@RequestParam("boardInfoIdx") int boardInfoIdx, ContentBean contentBean,
			Model model) {
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		
		return "board/main";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read() {
		return "board/read";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(ContentBean contentBean) {
		return "board/write";
	}
	
	@RequestMapping(value = "/write_pro", method = RequestMethod.POST)
	public String write_pro (@Valid ContentBean contentBean, BindingResult result) {
		if (result.hasErrors()) {
			
			return "board/write";
		} else {
			boardService.addContentInfo(contentBean);
			
			return "board/main";
		}
		
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
