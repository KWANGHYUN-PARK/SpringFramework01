package kr.co.service;

import java.util.List;

import kr.co.dao.Criteria;
import kr.co.dao.SearchCriteria;
import kr.co.domain.BoardVO;
import kr.co.domain.ReplyVO;

public interface BoardService {
	List<BoardVO> list();

	void insert(BoardVO vo);

	BoardVO read(int bno);

	BoardVO updateUI(int bno);

	void update(BoardVO vo);

	void delete(int bno);

	List<BoardVO> listPage(Criteria cri);

	int getAmount();

	List<BoardVO> search(SearchCriteria cri);

	int getSearchAmount(SearchCriteria cri);

	void delete(BoardVO vo);
	
	List<String> getAttach(Integer bno);
	
	void deleteAttach(String fileName, int bno);
	
}
