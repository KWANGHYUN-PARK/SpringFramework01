package kr.co.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dao.MessageDAO;
import kr.co.dao.UserDAO;
import kr.co.domain.BoardVO;
import kr.co.domain.MessageVO;
@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	@Inject
	private MessageDAO mDAO;
	
	@Inject
	private UserDAO uDAO;
	
	//메시지 생성+보내는 user에게 point 지급
	@Override
	public void create(MessageVO vo) {
		// TODO Auto-generated method stub
		mDAO.create(vo);
		uDAO.updatePoint(vo.getSender(), 10);
	}
	
	//메시지 읽어옴 + 읽는 user에게 point 지급+openDate 수정 (트랜젝션 써야함.)
	@Override
	public MessageVO readMessage(int mid, String userId) {
		// TODO Auto-generated method stub
		mDAO.updateState(mid);
		uDAO.updatePoint(userId, 5);
		
		
		return mDAO.readMessage(mid);
	}
	
	
}
