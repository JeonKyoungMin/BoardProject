package com.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
			 BindingResult result, Model model) {
		
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
	
	@RequestMapping(value = {"/findId", "/findPw"}, method = RequestMethod.GET)
	public String find(UserBean userBean, HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		
		if (requestUrl.equals("/user/findId")) {
			
			return "user/findId";
		} else {
			
			return "user/findPw";
		}
	}
	
	@RequestMapping(value = "/findId_pro", method = RequestMethod.POST)
	public String find_id_pro(UserBean userBean, Model model) {

		String result = userService.findUserId(userBean);

		if (result == null) {
			model.addAttribute("check", 1);
			
			return "user/findId";
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("result", result);
				
			return "user/findId";
		}
	}
	
	@RequestMapping(value= "/findPw_pro", method = RequestMethod.POST)
	public String find_pw_pro (UserBean userBean, Model model) {
		
		UserBean result = userService.findUserPw(userBean);
		
		if (result == null) {
			model.addAttribute("check", 1);
			
			return "user/findPw";
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("result", result);
			userBean.setUserLogin(true);
			
			return "user/findPw";
		}
	}
	
	@RequestMapping(value = "/modifyPw", method= RequestMethod.GET)
	public String modifyPw(UserBean userBean) {
		
		return "user/modifyPw";
	}
	
	@RequestMapping(value = "/modifyPw_pro", method = RequestMethod.POST)
	public String modifyPw_pro (UserBean userBean, Model model) {
		
		if (userBean.getUserPw() != userBean.getUserPw2()) {
			model.addAttribute("check", 1);
			
			return "user/modifyPw";
		} else {
			userService.modifyUserPw(userBean);
			
			return "user/login";
		}
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator userValidator = new UserValidator();
		binder.addValidators(userValidator);
	}
}
