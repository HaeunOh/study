package com.ezen.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">>> mvo >>> {}", mvo);
		int isOk = msv.insert(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpServletRequest request, Model m) {
		log.info(">>> mvo >>> {}", mvo);
		//mvo객체가 DB의 값과 일치하면 객체 가져오기 => 세션 저장
		MemberVO loginMvo = msv.isUser(mvo);
		
		log.info(">>> loginMvo >>> {}", loginMvo);
		
		
		if(loginMvo != null) {
			//로그인 성공시
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginMvo);
		}else {
			//로그인 실패
			m.addAttribute("msg_login", "1");
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		// last login 업데이트  => 세션 객체 삭제 => 세션 끊기
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.lastLoginUpdate(mvo.getId());
		
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_logout", "1");
		
		return "index";
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo) {
		log.info(">>> mvo >>> {}", mvo);
		msv.modify(mvo);
		return "redirect:/member/logout";
	}
	
	
	
	
}
