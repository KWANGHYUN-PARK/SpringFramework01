package kr.co.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{

	@Inject
	private SqlSession session;
	private final String NS="kr.co.mapper.message";
	
	
	@Override
	public void create(MessageVO vo) {
		// TODO Auto-generated method stub
		session.insert(NS+".create",vo);
	}

	@Override
	public MessageVO readMessage(int mid) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".readMessage", mid);
	}

	@Override
	public void updateState(int mid) {
		// TODO Auto-generated method stub
		session.update(NS+".updateState",mid);
	}

}
