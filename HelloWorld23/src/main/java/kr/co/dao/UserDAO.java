package kr.co.dao;

import kr.co.domain.UserVO;
import kr.co.domain.LoginDTO;

public interface UserDAO {
	void updatePoint(String userId, int userPoint);
	
	UserVO login(LoginDTO dto);
}
