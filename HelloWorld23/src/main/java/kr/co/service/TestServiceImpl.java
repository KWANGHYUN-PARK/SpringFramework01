package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.co.dao.TestDAO;
import kr.co.domain.TestDTO;

@Service
public class TestServiceImpl implements TestService {
	
	@Inject
	private TestDAO dao;

	@Override
	public void insert(TestDTO dto, Model model) {
		dao.insert(dto, model);
	}

	@Override
	public List<TestDTO> list() {
		return dao.list();
	}

	@Override
	public TestDTO selectByNum(int num) {
		
		return dao.selectByNum(num);
	}
	
	@Override
	public void delete(int num) {
		dao.delete(num);
	}

	
	@Override
	public TestDTO updateui(int num) {
		// TODO Auto-generated method stub
		return dao.updateui(num);
	}

	
	@Override
	public void update(TestDTO dto) {
		dao.update(dto);
		
	}

	


	
	
	
	

	
	
}
