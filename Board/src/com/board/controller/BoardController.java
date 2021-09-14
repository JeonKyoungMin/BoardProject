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
import com.board.beans.Criteria;
import com.board.beans.PageBean;
import com.board.beans.PageMaker;
import com.board.beans.ReplyBean;
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
					   ContentBean contentBean, Model model) {
		model.addAttribute("boardInfoIdx", boardInfoIdx);

		String result = boardService.getBoardInfo(boardInfoIdx);
		model.addAttribute("boardInfoName", result);

		List<ContentBean> contentList= boardService.getContentList(boardInfoIdx, page);
		model.addAttribute("contentList", contentList);
		
		PageBean pageBean = boardService.getContentCnt(boardInfoIdx, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("page", page);
		
		return "board/main";
	}
	
	@RequestMapping(value= "/listPage", method= RequestMethod.GET)
	public String list(@RequestParam("boardInfoIdx") int boardInfoIdx,
					   @RequestParam(value = "page", defaultValue = "1") int page,
					ContentBean contentBean, Criteria cri, Model model) {
		
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		
		String result = boardService.getBoardInfo(boardInfoIdx);
		model.addAttribute("boardInfoName", result);

		PageMaker pageMaker = new PageMaker(cri);
		int totalCount = boardService.totalCount(cri);
		pageMaker.setTotalCount(totalCount);
		
		List<ContentBean> contentList = boardService.listPage(cri);
		model.addAttribute("contentList", contentList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/listPage";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("boardInfoIdx") int boardInfoIdx,
					   @RequestParam("contentIdx") int contentIdx,
					   ReplyBean replyBean, Model model) {

		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("contentIdx", contentIdx);

		ContentBean result = boardService.getContentInfo(contentIdx);
		model.addAttribute("result", result);
		model.addAttribute("loginUserBean", loginUserBean);
		
		replyBean.setReplyBoardIdx(boardInfoIdx);
		replyBean.setReplyContentIdx(contentIdx);
		
		List<ReplyBean> replyList = replyService.selectReply(replyBean);
		
		model.addAttribute("replyList", replyList);
		
		return "board/read";
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("boardInfoIdx") int boardInfoIdx,
			@RequestParam("contentIdx") int contentIdx,
			ReplyBean replyBean, Model model) {
		
		model.addAttribute("boardInfoIdx", boardInfoIdx);
		model.addAttribute("contentIdx", contentIdx);
		
		ContentBean result = boardService.getContentInfo(contentIdx);
		model.addAttribute("result", result);
		model.addAttribute("loginUserBean", loginUserBean);
		
		replyBean.setReplyBoardIdx(boardInfoIdx);
		replyBean.setReplyContentIdx(contentIdx);
		
		List<ReplyBean> replyList = replyService.selectReply(replyBean);
		
		model.addAttribute("replyList", replyList);
		
		return "board/readPage";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@RequestParam("boardInfoIdx") int boardInfoIdx,
			ContentBean contentBean, Model model) {
		contentBean.setContentBoardIdx(boardInfoIdx);
		model.addAttribute("boardInfoIdx", boardInfoIdx);

		return "board/write";
	}
	
	@RequestMapping(value = "/write_pro", method = RequestMethod.POST)
	public String write_pro(@Valid ContentBean contentBean, Model model ,BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "board/write";
		} else {
			boardService.insertContent(contentBean);
			
			return "board/write_success";
		}
	}
	
	@RequestMapping(value = "/write_reply", method = RequestMethod.POST)
	public String write_reply(ReplyBean replyBean) {
		
		replyService.writeReply(replyBean);
		
		return "board/write_reply_success";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardInfoIdx") int boardInfoIdx,
						@RequestParam("contentIdx") int contentIdx, 
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
		
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify_pro", method= RequestMethod.POST)
	public String modify_pro(@Valid ContentBean contentBean, Model model, BindingResult result) {
		
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
	public String not_writer(@RequestParam("page") int page, 
            @RequestParam("perPageNum") int perPageNum, ContentBean contentBean,
            Model model) {
		
		return "board/not_writer";
	}
		
}
