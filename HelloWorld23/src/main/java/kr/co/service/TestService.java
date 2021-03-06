package kr.co.service;

import java.util.List;

import org.springframework.ui.Model;

import kr.co.domain.TestDTO;

public interface TestService {
	public void insert(TestDTO dto, Model model);
	List<TestDTO> list();
	TestDTO selectByNum(int num);
	void delete(int num);
	TestDTO updateui(int num);
	public void update(TestDTO dto);
	
}
