package com.ezen.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO udao;
	
	@Transactional
	@Override
	public int register(UserVO uvo) {
		log.info("user register in");
		//권한 추가
		int isOk = udao.register(uvo);
		
		return udao.insertAuthInit(uvo.getEmail());
	}

	@Override
	public List<UserVO> getList() {
		List<UserVO> list = udao.getList();
		for(int i=0; i<list.size(); i++) {
			String email = list.get(i).getEmail();
			List<AuthVO> alist = udao.selectAuths(email);
			list.get(i).setAuthList(alist);
		}
		
		log.info("get List >>> {}", list);
		return list;
	}


}
