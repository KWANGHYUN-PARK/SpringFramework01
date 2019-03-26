package kr.co.dao;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardDAO {
	List<BoardVO> list();

	void insert(BoardVO vo);

	BoardVO read(int bno);

	BoardVO updateUI(int bno);

	void update(BoardVO vo);
	
	void delete(BoardVO vo);

	List<BoardVO> listPage(Criteria cri);

	int getAmount();

	List<BoardVO> search(SearchCriteria cri);

	int getSearchAmount(SearchCriteria cri);

	void delete(int bno);

	void updateViewCnt(int bno);
	
	void incrViewCnt(int bno);
	
	void addAttach(String fullName, int bno);

	void deleteAttach(String fileName, int bno);

	void deleteAllAttach(int bno);
	
}
