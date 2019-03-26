package kr.co.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.TestDTO;
import kr.co.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Inject
	private TestService service;

	@RequestMapping("/insert1")
	public String insert(TestDTO dto, Model model) {
		service.insert(dto, model);
		model.addAttribute("keyTest", "valueTest");

		return "test";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<TestDTO> list = service.list();
		model.addAttribute("list", list);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		return "/test/list";
	}
	
	
	@RequestMapping("/insertui")
	public String insertui() {
		return "/test/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(TestDTO dto) {
		service.insert(dto, null);
		return "redirect:/test/list";
	}
	
	@RequestMapping("/selectByNum")
	public String selectBynum(Model model, int num) {//바인딩된 값을 가져갈 수 있도록 model 파라미터 추가
		
		TestDTO dto = service.selectByNum(num);
		model.addAttribute("dto", dto);
		return "/test/selectByNum";
	}
	
	@RequestMapping("/delete")
	public String delete(int num) {
		
		service.delete(num);
		return "redirect:/test/list";
	}
	
	@RequestMapping("/updateui")
	public String updateui(int num, Model model) {
		
		TestDTO dto = service.updateui(num);
		model.addAttribute("dto",dto);
		return "/test/update";//update.jsp를 지칭
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(TestDTO dto) {
		service.update(dto);
		return "redirect:/test/list";
	}

}
