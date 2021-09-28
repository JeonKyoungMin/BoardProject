package com.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
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
		
		UserBean login = userService.getloginBcryptUserInfo(tempLoginUserBean);
		
		boolean passMatch = pwdEncoder.matches(tempLoginUserBean.getUserPw(), login.getUserPw());

		if (result.hasErrors()) {
			
			return "user/login";
		} 
		
		if (loginUserBean.isUserLogin() == true && passMatch == true) {
			
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
						  BindingResult result) throws Exception {
		
		try {
			if (result.hasErrors()) {
				return "user/join";
			} else {
				String inputPass = joinUserBean.getUserPw();
				String pwd = pwdEncoder.encode(inputPass);
				joinUserBean.setUserPw(pwd);
				
				userService.addUser(joinUserBean);
				
				return "user/join_success";
			}
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@RequestMapping(value= {"/logout", "/not_login"}, method=RequestMethod.GET)
	public String logInfo(HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		
		if (requestUrl.equals("/user/logout")) {
			loginUserBean.setUserLogin(false);
			
			return "user/logout";
		} else {
			
			return "user/not_login";
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(UserBean userBean) {
		userBean.setUserId(loginUserBean.getUserId());
		userBean.setUserName(loginUserBean.getUserName());
		userService.getUserInfo(userBean);
		
		return "user/delete";
	}
	
	@RequestMapping(value="/delete_pro", method=RequestMethod.POST)
	public String delete_pro(UserBean userBean, Model model) {
		
		UserBean delete = userService.getloginBcryptUserInfo(userBean);
		
		boolean passMatch = pwdEncoder.matches(userBean.getUserPw(), delete.getUserPw());
		
		if (passMatch == true &&
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
	public String findId_pro(UserBean userBean, Model model) {

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
	public String findPw_pro (UserBean userBean, Model model) {
		
		UserBean result = userService.findUserPw(userBean);
		
		if (result == null) {
			model.addAttribute("check", 1);
			
			return "user/findPw";
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("result", result);
			
			return "user/findPw";
		}
	}
	
	
	@RequestMapping(value = {"/modifyPw", "/modify"} ,method= RequestMethod.GET)
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean, HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		
		if (requestUrl.equals("/user/modifyPw")) {
			
			return "user/modifyPw";
		} else {
			modifyUserBean.setUserId(loginUserBean.getUserId());
			modifyUserBean.setUserName(loginUserBean.getUserName());
			modifyUserBean.setUserIdx(loginUserBean.getUserIdx());
			userService.getUserInfo(modifyUserBean);
			
			return "user/modify";
		}
	}
	
	@RequestMapping(value= "/modify_pro",method=RequestMethod.POST)
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "user/modify";
		} else {
			
			String inputPass = modifyUserBean.getUserPw();
			String pwd = pwdEncoder.encode(inputPass);
			modifyUserBean.setUserPw(pwd);
			
			userService.modifyUserInfo(modifyUserBean);
			
			return "redirect:/main";
		}
		
	}
	
	@RequestMapping(value = "/modifyPw_pro", method = RequestMethod.POST)
	public String modifyPw_pro (@ModelAttribute("modifyUserBean") UserBean modifyUserBean, Model model) {
		
		if (modifyUserBean.getUserPw().length() == 0 || !(modifyUserBean.getUserPw().equals(modifyUserBean.getUserPw2())) ) {
			
			return "user/modifyPw";
		} else {
			
			String inputPass = modifyUserBean.getUserPw();
			String pwd = pwdEncoder.encode(inputPass);
			modifyUserBean.setUserPw(pwd);
			
			userService.modifyUserPw(modifyUserBean);
			
			return "redirect:/user/login";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator userValidator = new UserValidator();
		binder.addValidators(userValidator);
	}
}
