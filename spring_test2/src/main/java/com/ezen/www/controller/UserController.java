package com.ezen.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.www.domain.UserVO;
import com.ezen.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user/**")
public class UserController {

	
	@Autowired
	private UserService usv;
	
	@Autowired
	private BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(UserVO uvo) {
		log.info(">>> register user >>>> {}", uvo);
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		int isOk = usv.register(uvo);
		return "index";
	}
}
