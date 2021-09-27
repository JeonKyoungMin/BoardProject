package com.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.beans.BoardInfoBean;
import com.board.beans.ContentBean;
import com.board.service.MainService;
import com.board.service.TopMenuService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;
	
	@GetMapping("/main")
	public String main(Model model) {
		
		ArrayList<List<ContentBean>> list = new ArrayList<List<ContentBean>>();

		for(int i = 1; i <= 4; i++) {
			List<ContentBean> list1 = mainService.getMainList(i);
			list.add(list1);
		}
		
		List<BoardInfoBean> boardList = topMenuService.getTopMenuList();
		 
		model.addAttribute("list", list);
		model.addAttribute("boardList", boardList);
		
		return "main";
	}
}

