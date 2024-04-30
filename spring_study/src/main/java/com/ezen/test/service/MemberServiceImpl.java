package com.ezen.test.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public int insert(MemberVO mvo) {
		//id 중복되는 경우 회원가입 실패
		//아이디만 주고, DB에서 일치하는 mvo 객체를 리턴 => 일치하는 객체가 있으면 가입실패, 없으면 가능
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		if(tempMvo != null) {
			//아이디가 중복되는 상황 
			return 0;
		}
		//아이디 or 패스워드가 비어있다면? null이거나 값이 없다면 ...
		if(mvo.getId() == null || mvo.getId().length() == 0) {
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
		}
		
		//위 사항 모두 문제없다면 회원가입 진행
		//password 암호화하여 가입
		//encode() : 암호화 진행 메서드 / matches(입력된비번, 암호화된비번) => true/false로 리턴
		
//		String pw = mvo.getPw();
//		String encodePw = passwordEncoder.encode(pw);
//		mvo.setPw(encodePw);
//		위 세줄을 밑에 한줄로 줄인 것
		
		mvo.setPw(passwordEncoder.encode(mvo.getPw()));
		
		int isOk = mdao.insert(mvo);
		return isOk;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		//로그인 유저 확인하기
		log.info("loginUser in !! ");
		MemberVO tempMvo = mdao.getUser(mvo.getId()); //회원가입시 썼던 메서드 활용
		
		//해당 아이디가 없으면...
		if(tempMvo == null) {
			return null;
		}
		
		//matches(원래 비번, 암호화 된 비번 비교)
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
		
		return null;
	}

	@Override
	public void lastLoginUpdate(String id) {
		 mdao.lastLoginUpdate(id);
		
	}

	@Override
	public void modify(MemberVO mvo) {
		//pw 여부에 따라 변경 사항을 나누어서 처리
		//pw가 없다면 기존값 설정, 있다면 암호화 처리하여 수정
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			MemberVO sesMvo  = (MemberVO)request.getSession().getAttribute("ses");
			mvo.setPw(sesMvo.getPw());
		}else {
			String setPw = passwordEncoder.encode(mvo.getPw());
			mvo.setPw(setPw);
		}
		
		log.info(">>> pw 수정 후 mvo >>> {}", mvo);
		mdao.update(mvo);
	}

	@Override
	public void remove(String id) {
		log.info("remove service in!");
		mdao.remove(id);
		
	}
	
}
