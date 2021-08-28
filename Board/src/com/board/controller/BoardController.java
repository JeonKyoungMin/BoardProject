package com.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.beans.ContentBean;
import com.board.beans.UserBean;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired	
	private BoardService boardService;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(@RequestParam("boardInfoIdx") int boardInfoIdx, ContentBean contentBean,
			Model model) {
		String result = boardService.getBoardInfo(boardInfoIdx);

		List<ContentBean> contentList= boardService.getContentList(boardInfoIdx);
		
		model.addAttribute("contentList", contentList);
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("boardInfoName", result);
		
		
		return "board/main";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("boardInfoIdx") int boardInfoIdx,
					   @RequestParam("contentIdx") int contentIdx, Model model) {
		ContentBean result = boardService.getContentInfo(contentIdx);
		
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("contentIdx", contentIdx);
		model.addAttribute("result", result);
		model.addAttribute("loginUserBean", loginUserBean);
		
		return "board/read";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@RequestParam("boardInfoIdx") int boardInfoIdx,
			ContentBean contentBean) {
		contentBean.setContentBoardIdx(boardInfoIdx);
		
		return "board/write";
	}
	
	@RequestMapping(value = "/write_pro", method = RequestMethod.POST)
	public String write_pro (@Valid ContentBean contentBean, BindingResult result) {
		if (result.hasErrors()) {
			
			return "board/write";
		} else {
			boardService.insertContent(contentBean);
			
			return "main";
		}
		
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardInfoIdx") int boardInfoIdx,
						@RequestParam("contentIdx") int contentIdx, ContentBean contentBean,
						Model model) {
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("contentIdx", contentIdx);
		
		ContentBean result = boardService.getContentInfo(contentIdx);
		
		contentBean.setContentWriterName(result.getContentWriterName());
		contentBean.setContentYmd(result.getContentYmd());
		contentBean.setContentTtl(result.getContentTtl());
		contentBean.setContentCont(result.getContentCont());
		contentBean.setContentFile(result.getContentFile());
		contentBean.setContentIdx(result.getContentWriterIdx());
		contentBean.setContentBoardIdx(boardInfoIdx);
		contentBean.setContentIdx(contentIdx);
		
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify_pro", method= RequestMethod.POST)
	public String modify_pro(@Valid ContentBean contentBean, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "/board/modify";
		} else {
			boardService.modifyContent(contentBean);

			return "board/modify_success";
		}
		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("boardInfoIdx")int boardInfoIdx,
						 @RequestParam("contentIdx")int contentIdx, Model model) {
		boardService.deleteContent(contentIdx);
		
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		
		return "board/delete";
	}

	@RequestMapping(value = "/not_writer", method = RequestMethod.GET)
	public String not_writer() {
		
		return "board/not_writer";
	}
}
