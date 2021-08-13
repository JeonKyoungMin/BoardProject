package com.board.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.board.beans.UserBean;
import com.board.dao.UserDao;

/**
 * @author 전경민
 * @date 2021-08
 *  
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Resource(name= "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	/**
	 * <p>아이디 중복 검사</p>
	 */
	@Override
	public boolean checkUserIdExist(String userId) {
		String userName = userDao.checkUserIdExist(userId);
	
		if (userName == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * <p>회원가입 (유저 정보 삽입)</p>
	 */
	@Override
	public void addUser(UserBean joinUserBean) {
		userDao.addUser(joinUserBean);
	}
	
	@Override
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		
		if (tempLoginUserBean2 != null) {
			loginUserBean.setUserIdx(tempLoginUserBean2.getUserIdx());
			loginUserBean.setUserName(tempLoginUserBean2.getUserName());
			loginUserBean.setUserLogin(true);
		}
		
	}
	
	@Override
	public void getUserInfo(UserBean userBean) {
		UserBean result = userDao.getUserInfo(userBean);
		
		if (result != null) {
			userBean.setUserName(result.getUserName());
			userBean.setUserId(result.getUserId());
			userBean.setUserIdx(result.getUserIdx());
			loginUserBean.setUserNum(result.getUserNum());
			loginUserBean.setUserPw(result.getUserPw());
		}
	}
	
	@Override
	public void deleteUser(UserBean userBean) {
		userDao.deleteUser(userBean);
		
	}
	
	@Override
	public String findUserId(UserBean userBean) {
		String result = userDao.findUserId(userBean);
		
		return result;
	}
	
	@Override
	public UserBean findUserPw(UserBean userBean) {
		UserBean result = userDao.findUserPw(userBean); 
		
		return result;
	}
}
