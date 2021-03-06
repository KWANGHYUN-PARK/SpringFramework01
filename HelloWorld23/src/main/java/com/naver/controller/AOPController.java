package com.naver.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MessageVO;
import kr.co.service.MessageService;

@Controller
@RequestMapping("aop")
public class AOPController {
	
	@Inject
	private MessageService service;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(MessageVO vo) {
		System.out.println(vo);
		service.create(vo);
		return "/aop/list";
		//일반 컨트롤러에서 리턴값이 의미하는 것 : 뷰에 대한 정보
	}
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public void create() {
		
	}
}
