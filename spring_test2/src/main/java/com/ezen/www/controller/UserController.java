package com.ezen.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.BoardVO;
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
	@GetMapping("/login")
	public void login () {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지로 돌아와오류 메세지 전송
		//다시 로그인 유도
		log.info(">>> errMsg >> {} ", request.getAttribute("errMsg").toString());
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return "redirect:/user/login";
	}
	
	@GetMapping("/list")
	public String list(Model m) {
		List<UserVO> list = usv.getList();	
		m.addAttribute("list", list);
		
		return "/user/list";
	}
	
	@GetMapping("/modify")
	public void modify () {}
	
	
}
