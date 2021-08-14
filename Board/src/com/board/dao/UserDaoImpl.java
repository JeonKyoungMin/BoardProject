package com.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.UserBean;

/**
 * @author 전경민
 * @date 2021-08
 * 
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * <p>아이디 중복 검사</p>
	 */
	@Override
	public String checkUserIdExist(String userId) {
		String result = sqlSessionTemplate.selectOne("user.checkUserIdExist", userId);
		
		return result;
	}
	
	/**
	 * <p>회원가입 (유저 정보 삽입)</p>
	 */
	@Override
	public void addUser(UserBean joinUserBean) {
		sqlSessionTemplate.insert("user.addUser", joinUserBean);
		
	}
	
	/**
	 * <p>로그인 유저의 정보 검색</p>
	 */
	@Override
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean)  {
		UserBean result = sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
		
		return result;
	}	
	
	/**
	 * <p>유저 정보 검색</p>
	 */
	@Override
	public UserBean getUserInfo(UserBean userBean) {
		UserBean result = sqlSessionTemplate.selectOne("user.getUserInfo", userBean);
		
		return result;
	}
	
	/**
	 * <p>회원 탈퇴</p>
	 */
	@Override
	public void deleteUser(UserBean userBean) {
		sqlSessionTemplate.delete("user.deleteUser", userBean);
	}
	
	/**
	 * <p>유저 아이디 찾기</p>
	 */
	@Override
	public String findUserId(UserBean userBean) {
		String result = sqlSessionTemplate.selectOne("user.findUserId",userBean);
		
		return result;
	}
	
	
	/**
	 * <p>비밀번호 변경을 위해 유저 정보 검색</p>
	 */
	@Override
	public UserBean findUserPw(UserBean userBean) {
		UserBean result = sqlSessionTemplate.selectOne("user.findUserPw", userBean);
		
		return result;
	}
	
	
	/**
	 * <p>비밀번호 변경을 위해 정보 검색 후 정보가 있다면 유저 비밀번호 재설정</p>
	 */
	@Override
	public void modifyUserPw(UserBean modifyUserBean) {
		sqlSessionTemplate.update("user.modifyUserPw", modifyUserBean);
	}
	
	/**
	 * <p>회원 정보 수정 (비밀번호, 주소, 전화번호)</p>
	 */
	@Override
	public void modifyUserInfo(UserBean userBean) {
		sqlSessionTemplate.update("user.modifyUserInfo", userBean);
	}
}
