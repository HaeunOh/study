package com.ezen.www.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
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

	@Override
	public void modify(UserVO uvo) {
		if(uvo.getPwd() == null || uvo.getPwd().length() == 0) {
			udao.updateNicknameOnly(uvo);
		}else {
			String setPW = passwordEncoder.encode(uvo.getPwd());
			uvo.setPwd(setPW);
			udao.updateUserInfo(uvo);
		}
		log.info(">>> pwd 수정 후 uvo >> {}", uvo);
		
	}

	@Override
	public int remove(String myName) {
		log.info("user remove servie in");
		int isOk = udao.removeAuth(myName);
		if(isOk > 0) {
			udao.removeUser(myName);
		}
		return isOk;
	}

	@Override
	public int checkEmail(String email) {
		// TODO Auto-generated method stub
		return udao.checkEmail(email);
	}


}
