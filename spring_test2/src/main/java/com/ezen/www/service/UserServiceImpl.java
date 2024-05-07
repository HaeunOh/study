package com.ezen.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO udao;

	@Override
	public int register(UserVO uvo) {
		// TODO Auto-generated method stub
		return udao.register(uvo);
	}
}
