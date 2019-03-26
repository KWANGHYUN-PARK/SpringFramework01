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
import kr.co.dao.ReplyDAO;
import kr.co.dao.SearchCriteria;
import kr.co.domain.BoardVO;
import kr.co.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class ReplyDAOTest {
	@Autowired//@Inject를 사용해도 동일한 효과?
	private ReplyDAO dao;
	
	@Test
	public void testInsert() {
		ReplyVO vo = new ReplyVO(3, 35, "sadlkfjsadf", "ddd", null, null);
		dao.insert(vo);
		
		//댓글을 달고자 하는 글의 번호 = 35
	}
	@Test
	public void testList() {
		List<ReplyVO> list = dao.list(35);
		for(ReplyVO vo:list) {
			System.out.println(vo);
		}
	}
	
	@Test
	public void testUpdate() {
		
		ReplyVO vo = new ReplyVO(1, 35, "수정", null, null, null);
		dao.update(vo);
	}
	
	@Test
	public void testDelete() {
		int rno = 19;
		dao.delete(rno);
	}
	
}
