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
	
	/**
	 * <p>로그인 한 유저의 정보 검색 </p>
	 */
	@Override
	public UserBean getloginBcryptUserInfo(UserBean tempLoginUserBean) {
		UserBean result = userDao.getloginBcryptUserInfo(tempLoginUserBean);
		
		if (result != null) {
			loginUserBean.setUserIdx(result.getUserIdx());
			loginUserBean.setUserId(result.getUserId());
			loginUserBean.setUserName(result.getUserName());
			loginUserBean.setUserLogin(true);
		}
		
		return result;
	}
	
	/**
	 * <p>유저 정보 검색</p>
	 */
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
	
	/**
	 * <p>회원 탈퇴</p>
	 */
	@Override
	public void deleteUser(UserBean userBean) {
		userDao.deleteUser(userBean);
		
	}
	
	/**
	 * <p>유저 아이디 찾기</p>
	 */
	@Override
	public String findUserId(UserBean userBean) {
		String result = userDao.findUserId(userBean);
		
		return result;
	}
	
	/**
	 * <p>비밀번호 변경을 위해 유저 정보 검색</p>
	 */
	@Override
	public UserBean findUserPw(UserBean userBean) {
		UserBean result = userDao.findUserPw(userBean); 
		
		if (result != null) {
			userBean.setUserIdx(result.getUserIdx());
			userBean.setUserId(result.getUserId());
			userBean.setUserName(result.getUserName());
		}
		return result;
	}
	
	/**
	 * <p>비밀번호 변경을 위해 정보 검색 후 정보가 있다면 유저 비밀번호 재설정</p>
	 */
	@Override
	public void modifyUserPw(UserBean modifyUserBean) {
		userDao.modifyUserPw(modifyUserBean);
	}
	
	/**
	 * <p>회원 정보 수정 (비밀번호, 전화번호 , 주소)</p>
	 */
	@Override
	public void modifyUserInfo(UserBean userBean) {
		userDao.modifyUserInfo(userBean);
	}
}
