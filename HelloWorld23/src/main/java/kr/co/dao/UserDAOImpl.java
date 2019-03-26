package kr.co.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.UserVO;
import kr.co.domain.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SqlSession session;
	private String NS="kr.co.mapper.user";
	
	
	@Override
	public void updatePoint(String userId, int userPoint) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(map);
		map.put("userId", userId);
		map.put("userPoint", userPoint);
		session.update(NS+".updatePoint", map);
	}


	@Override
	public UserVO login(LoginDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".login", dto);
	}


	

}
