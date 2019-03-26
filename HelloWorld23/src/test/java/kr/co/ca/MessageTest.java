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
import kr.co.dao.MessageDAO;
import kr.co.dao.SearchCriteria;
import kr.co.domain.BoardVO;
import kr.co.domain.MessageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MessageTest {
	@Autowired//@Inject를 사용해도 동일한 효과?
	private MessageDAO dao;
	
	@Test
	public void testCreate() {
		MessageVO vo=new MessageVO(1, "user1", "user2", "유저2가 유저1에게 보내는 메세지", null, null);
		dao.create(vo);
	}
	
	@Test
	public void testUpdateState() {
		
		dao.updateState(1);
	}
	
	@Test
	public void testReadMessage() {
		MessageVO vo=dao.readMessage(1);
		System.out.println(vo);
	}
}