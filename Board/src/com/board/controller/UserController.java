package com.board.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.beans.UserBean;
import com.board.service.UserService;
import com.board.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Resource(name= "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail,
						Model model) {
		
		model.addAttribute("fail", fail);
		
		return "user/login";
	}
	
	@RequestMapping(value="/login_pro", method=RequestMethod.POST)
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "user/login";
		} 
		
		userService.getLoginUserInfo(tempLoginUserBean);
		
		
		if (loginUserBean.isUserLogin() == true) {
			return "user/login_success";
		} else {
			return "user/login_fail";
		}
		
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}
	
	@RequestMapping(value="/join_pro", method=RequestMethod.POST)
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean,
						  BindingResult result) {
		if (result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUser(joinUserBean);
		return "user/join_success";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify() {
		return "user/modify";
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@RequestMapping(value="/not_login", method=RequestMethod.GET)
	public String not_login() {
	
		return "user/not_login";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(UserBean userBean) {
		userBean.setUserIdx(loginUserBean.getUserIdx());
		userService.getUserInfo(userBean);

		return "user/delete";
	}
	
	@RequestMapping(value="/delete_pro", method=RequestMethod.POST)
	public String delete_pro(UserBean userBean, Model model) {
		
		if (userBean.getUserPw().equals(loginUserBean.getUserPw()) &&
			userBean.getUserNum().equals(loginUserBean.getUserNum()))  {
			userService.deleteUser(userBean);
			loginUserBean.setUserLogin(false);
			
			return "redirect:/main";
		} else {
			model.addAttribute("check", 1);
			
			return "user/delete";
		}
			
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator userValidator = new UserValidator();
		binder.addValidators(userValidator);
	}
}
