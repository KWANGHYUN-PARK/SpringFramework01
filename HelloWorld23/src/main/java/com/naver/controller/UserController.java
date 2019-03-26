package com.naver.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.LoginDTO;
import kr.co.domain.UserVO;
import kr.co.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Inject
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGet(){
		
	}
	
	
	@RequestMapping(value="/loginPost",method=RequestMethod.POST)
	public void loginPost(LoginDTO dto, Model model) throws Exception{
		UserVO userVO=service.login(dto);
		
		if(userVO==null) {
			return;
			/*
			 * userVO가 null일 때 바로 return; 지정하여 바인딩X userVO값이 있든 없든 /user/loginpost로 이동.. 바인딩
			 * 값이 있는지 없는지의 차이
			 */
		}
		model.addAttribute("userVO", userVO);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
