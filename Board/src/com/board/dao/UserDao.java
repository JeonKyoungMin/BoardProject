package com.board.dao;

import org.springframework.stereotype.Repository;

import com.board.beans.UserBean;

@Repository
public interface UserDao {

	public String checkUserIdExist(String userId);
	
	public void addUser(UserBean joinUserBean);
	
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	public UserBean getUserInfo(UserBean userBean);
	
	public void deleteUser(UserBean userBean);

}
