package kr.co.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import kr.co.domain.TestDTO;

@Repository
public class TestDAOImpl implements TestDAO {
	@Inject
	private SqlSession sqlSession;
	private final String NS = "kr.co.mapper.test";

	@Override
	public void insert(TestDTO dto, Model model) {
		sqlSession.insert(NS + ".insert", dto);
	}

	@Override
	public List<TestDTO> list() {
		return sqlSession.selectList(NS + ".list");
	}
	
	@Override
	public TestDTO selectByNum(int num) {
		return sqlSession.selectOne(NS + ".selectByNum",num);
	}
	
	@Override
	public void delete(int num) {
		sqlSession.delete(NS + ".delete", num);
	}

	@Override
	public TestDTO updateui(int num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".updateui",num);
	}

	@Override
	public void update(TestDTO dto) {
		
		sqlSession.update(NS+".update", dto);
	}

	
	
	

	
	
	

}
