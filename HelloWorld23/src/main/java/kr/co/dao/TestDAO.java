package kr.co.dao;

import java.util.List;

import org.springframework.ui.Model;

import kr.co.domain.TestDTO;

public interface TestDAO {
	public void insert(TestDTO dto, Model model);
	public List<TestDTO> list();
	TestDTO selectByNum(int num);
	public void delete(int num);
	
	public TestDTO updateui(int num);
	
	void update(TestDTO dto);

	
}
