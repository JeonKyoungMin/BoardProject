package com.board.dao;

import org.springframework.stereotype.Repository;

import com.board.beans.UserBean;

/**
 * @author km
 * @date 2021-08
 */
@Repository
public interface UserDao {

	/**
	 * <pre>
	 * 처리내용 : 아이디 중복확인
	 */
	public String checkUserIdExist(String userId);
	
	/**
	 * <pre>
	 * 처리내용 : 유저 회원가입
	 */
	public void addUser(UserBean joinUserBean);
	
	/**
	 * <pre>
	 * 처리내용 : 로그인 유저의 정보 가져오기
	 */
	public UserBean getloginBcryptUserInfo(UserBean tempLoginUserBean);
	
	/**
	 * <pre>
	 * 처리내용 : 유저 정보 가져오기
	 */
	public UserBean getUserInfo(UserBean userBean);
	
	/**
	 * <pre>
	 * 처리내용 : 유저 정보 삭제 (탈퇴)
	 */
	public void deleteUser(UserBean userBean);

	/**
	 * <pre>
	 * 처리내용 : 유저 아이디 정보 찾기
	 */
	public String findUserId(UserBean userBean);
	
	/**
	 * <pre>
	 * 처리내용 : 유저 비밀번호 찾기 위한 유저 정보 가져오기
	 */
	public UserBean findUserPw(UserBean userBean);
	
	/**
	 * <pre>
	 * 처리내용 : 유저 비밀번호 변경
	 */
	public void modifyUserPw(UserBean modifyUserBean);
	
	/**
	 * <pre>
	 * 처리내용 : 유저 정보 변경
	 */
	public void modifyUserInfo(UserBean userBean);
	
}
