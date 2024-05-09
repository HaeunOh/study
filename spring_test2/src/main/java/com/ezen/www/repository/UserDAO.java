package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;

public interface UserDAO {

	int register(UserVO uvo);

	int insertAuthInit(String email);

	UserVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<UserVO> getList();

	void updateUserInfo(UserVO uvo);

	void updateNicknameOnly(UserVO uvo);

	int removeAuth(String myName);

	void removeUser(String myName);

	int checkEmail(String email);


}
