package com.ezen.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PostMapping(value="/idCheck", consumes="text/plain", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> idCheck(@RequestBody String email) {
		log.info(email);
		int isOk = usv.checkEmail(email);
		return isOk > 0? new ResponseEntity<String>("1",HttpStatus.OK) :
			new ResponseEntity<String>("-1", HttpStatus.INTERNAL_SERVER_ERROR);
		
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
	
	@PostMapping("/modify")
	public String modify(UserVO uvo, HttpServletRequest request, HttpServletResponse response) {
		log.info(">>> modify uvo >> {}", uvo);
		usv.modify(uvo);
		logout(request, response);
		//return "index"; 로 보내면 내가 header에서 설정해놓은 권한에 대한 정보가 없이 인덱스로 넘어가게됨
		//"redirect:/" 로 root -> HomeController 호출하여 설정값이 있는 인덱스로 이동!
		return "redirect:/";
	}
	
	//호출하면 로그아웃되는 메서드
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}
	
	@GetMapping("/remove")
	public String remove(HttpServletRequest request, HttpServletResponse response, RedirectAttributes re, Principal principal) {
		String myName = principal.getName();
		int isOk = usv.remove(myName);
		if(isOk > 0) {
			log.info("user remove done!");
		}
		logout(request, response);
		//model -> jsp로만 Attribute 보낼 수 있음. redirect 일 땐 RedirectAttributes 사용.
		re.addAttribute("deleteMsg", "1");
		return "redirect:/";
	}
	
}
