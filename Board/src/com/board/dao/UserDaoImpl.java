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
	
	@Override
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean)  {
		UserBean result = sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
		
		return result;
	}
	
	@Override
	public UserBean getUserInfo(UserBean userBean) {
		UserBean result = sqlSessionTemplate.selectOne("user.getUserInfo", userBean);
		
		return result;
	}
	
	@Override
	public void deleteUser(UserBean userBean) {
		sqlSessionTemplate.delete("user.deleteUser", userBean);
	}
	
	@Override
	public String findUserId(UserBean userBean) {
		String result = sqlSessionTemplate.selectOne("user.findUserId",userBean);
		
		return result;
	}
	
	@Override
	public UserBean findUserPw(UserBean userBean) {
		UserBean result = sqlSessionTemplate.selectOne("user.findUserPw", userBean);
		
		return result;
	}
	
	@Override
	public void modifyUserPw(UserBean userBean) {
		sqlSessionTemplate.update("user.modifyUserPw", userBean);
	}
}
