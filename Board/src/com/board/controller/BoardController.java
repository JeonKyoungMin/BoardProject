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
import com.board.beans.PageBean;
import com.board.beans.UserBean;
import com.board.service.BoardService;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired	
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(@RequestParam("boardInfoIdx") int boardInfoIdx,
					   @RequestParam(value = "page", defaultValue = "1") int page,
					   ContentBean contentBean,
			Model model) {
		model.addAttribute("boardInfoIdx", boardInfoIdx);

		String result = boardService.getBoardInfo(boardInfoIdx);
		model.addAttribute("boardInfoName", result);

		List<ContentBean> contentList= boardService.getContentList(boardInfoIdx, page);
		model.addAttribute("contentList", contentList);
		
		PageBean pageBean = boardService.getContentCnt(boardInfoIdx, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("page", page);
		
		int result1 = replyService.countReply();
		
		System.out.println(result1);
		
		return "board/main";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("boardInfoIdx") int boardInfoIdx,
					   @RequestParam("contentIdx") int contentIdx,
					   @RequestParam("page") int page, 
					   Model model) {
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("contentIdx", contentIdx);

		ContentBean result = boardService.getContentInfo(contentIdx);
		model.addAttribute("result", result);
		model.addAttribute("loginUserBean", loginUserBean);
		model.addAttribute("page", page);
		
		
		return "board/read";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@RequestParam("boardInfoIdx") int boardInfoIdx,
			ContentBean contentBean, Model model) {
		contentBean.setContentBoardIdx(boardInfoIdx);
		model.addAttribute("boardInfoIdx", boardInfoIdx);

		return "board/write";
	}
	
	@RequestMapping(value = "/write_pro", method = RequestMethod.POST)
	public String write_pro (@Valid ContentBean contentBean, Model model ,BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "board/write";
		} else {
			boardService.insertContent(contentBean);
			
			return "board/write_success";
		}
		
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardInfoIdx") int boardInfoIdx,
						@RequestParam("contentIdx") int contentIdx, 
						@RequestParam("page") int page,
						ContentBean contentBean, Model model) {
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
		
		model.addAttribute("page", page);
		
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify_pro", method= RequestMethod.POST)
	public String modify_pro(@Valid @RequestParam("page") int page,
					ContentBean contentBean, Model model,BindingResult result) {
		
		model.addAttribute("page", page);
		
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
