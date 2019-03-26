package kr.co.ca;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.dao.BoardDAO;
import kr.co.dao.SearchCriteria;
import kr.co.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {
	@Autowired//@Inject를 사용해도 동일한 효과?
	private BoardDAO dao;
	
	@Test
	public void testSearch() {
		SearchCriteria cri = new SearchCriteria("writer","1313");
		List<BoardVO> list=dao.search(cri);
		for(BoardVO vo:list) {
			System.out.println(vo);
		}
	}
	
	@Test
	public void getSearchAmount() {
		SearchCriteria cri = new SearchCriteria("writer","1");
		int i = dao.getSearchAmount(cri);
		System.out.println(i+";;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
	}
	
	@Test
	public void testList() {
		List<BoardVO> list = dao.list();
		for(BoardVO vo:list) {
			System.out.println(vo);
		}
	}
}
