package com.board.beans;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserBean {

	private int userIdx;
	
	@Size(min=2, max=4)
	@Pattern(regexp = "[가-힣]*")
	private String userName;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userId;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userPw;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userPw2;
	
	@Size(min=1, max=100)
	private String userAdd1;

	private String userAdd2;
	
	@Size(min=1, max=100)
	private String userAdd3;
	
	private String userNum;
	
	private boolean userIdExist;
	
	private boolean userLogin;
	
	public UserBean() {
		this.userIdExist = false;
		this.userLogin = false;
	}
}
