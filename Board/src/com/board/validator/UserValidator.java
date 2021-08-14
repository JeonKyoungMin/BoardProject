package com.board.validator;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.board.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserBean.class.isAssignableFrom(clazz);
	}
	
	/**
	 * <p>각각의 BeanName에 따라 입력값 Validation Check</p>
	 */
	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean)target;
		
		String beanName = errors.getObjectName();
		
		if(beanName.equals("joinUserBean")) {
			if (userBean.getUserPw().equals(userBean.getUserPw2()) == false) {
				errors.rejectValue("userPw", "NotEquals");
				errors.rejectValue("userPw2", "NotEquals");
			}
			
			if (userBean.isUserIdExist() == false) {
				errors.rejectValue("userId", "DontCheckUserIdExist");
			}
		} else if (beanName.equals("modifyUserBean")) {
			if (userBean.getUserPw().equals(userBean.getUserPw2()) == false) {
				errors.rejectValue("userPw", "NotEquals");
				errors.rejectValue("userPw2", "NotEquals");
			}

		}
	}
}

