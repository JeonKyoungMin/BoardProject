package com.board.service;

import org.springframework.stereotype.Service;

import com.board.beans.UserBean;

/**
 * @author km
 * @date 2021-08
 */
@Service
public interface UserService {

	/**
	 * <p>아이디 중복 체크</p>
	 */	
	public boolean checkUserIdExist(String userId);
	
	/**
	 * <p>유저 회원가입</p>
	 */
	public void addUser(UserBean joinUserBean);
	
	/**
	 * <p>로그인 정보 가져오기</p>
	 */
	public void getLoginUserInfo(UserBean tempLoginUserBean);

	/**
	 * <p>유저 정보 가져오기</p>
	 */
	public void getUserInfo(UserBean userBean);
	
	/**
	 * <p>유저 탈퇴</p>
	 */
	public void deleteUser(UserBean userBean);
	
	/**
	 * <p>유저 아이디 찾기</p>
	 */
	public String findUserId(UserBean userBean);
	
	/**
	 * <p>유저 비밀번호 변경을 위해 유저 정보 찾기</p>
	 */
	public UserBean findUserPw(UserBean userBean);
	
	/**
	 * <p>유저 비밀번호 변경</p>
	 */
	public void modifyUserPw(UserBean modifyUserBean);
	
	/**
	 * <p>유저 정보 변경</p>
	 */
	public void modifyUserInfo(UserBean userBean);
}
