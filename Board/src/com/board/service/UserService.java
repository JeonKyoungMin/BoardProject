package com.board.service;

import org.springframework.stereotype.Service;

import com.board.beans.UserBean;

@Service
public interface UserService {

	public boolean checkUserIdExist(String userId);
	
	public void addUser(UserBean joinUserBean);
	
	public void getLoginUserInfo(UserBean tempLoginUserBean);

	public void getUserInfo(UserBean userBean);
	
	public void deleteUser(UserBean userBean);
	
	public String findUserId(UserBean userBean);
	
	public UserBean findUserPw(UserBean userBean);
}
