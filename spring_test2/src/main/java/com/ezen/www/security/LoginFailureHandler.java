package com.ezen.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
@Setter
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private String authEmail;
	private String errorMsg;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		setAuthEmail(request.getParameter("email"));
		
		//exception 발생시 메세지 저장.
		//|| exception instanceof InternalAuthenticationServiceException
		if(exception instanceof BadCredentialsException ) {
			setErrorMsg(exception.getMessage().toString());		
		}
		
		log.info(">>> errMsg >>> {}", this.errorMsg);
		request.setAttribute("email", authEmail);
		request.setAttribute("errMsg", errorMsg);
		
		request.getRequestDispatcher("/user/login?error").forward(request, response);
	}

}
