package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dao.BoardDAO;
import kr.co.dao.Criteria;
import kr.co.dao.ReplyDAO;
import kr.co.dao.SearchCriteria;
import kr.co.domain.BoardVO;
import kr.co.domain.ReplyVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO dao;
	
	@Inject
	private ReplyDAO rdao;
	
	@Override
	public void insert(BoardVO vo) {
		dao.insert(vo);
		String[] files=vo.getFiles();
		if(files==null) {
			return;
		}
		for(int i=0;i<files.length;i++) {
			dao.addAttach(files[i],vo.getBno());
		}
	}
	
	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		dao.incrViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public BoardVO updateUI(int bno) {
		// TODO Auto-generated method stub
		return dao.updateUI(bno);
	}

	@Override
	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
		dao.deleteAllAttach(vo.getBno());
		String[] files=vo.getFiles();
		if(files ==null) {
			return;
		}else {
			for(String fullName:files) {
				dao.addAttach(fullName,vo.getBno());
			}
		}
	}

	@Override
	public void delete(BoardVO vo) {
		// TODO Auto-generated method stub
		
		dao.delete(vo);
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.listPage(cri);
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return dao.getAmount();
	}

	@Override
	public List<BoardVO> search(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.search(cri);
	}

	@Override
	public int getSearchAmount(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.getSearchAmount(cri);
	}

	@Override
	public void delete(int bno) {
		// TODO Auto-generated method stub
		rdao.deleteByBno(bno);
		dao.deleteAllAttach(bno);
		dao.delete(bno);
		//원글을 지우기 전에 bno를 참조하는 댓글들을 전부 지움. 코드 순서 중요 dao.delete(bno)보다 위에 위치해야 한다.
		
		}

	@Override
	public List<String> getAttach(Integer bno){
		
		return rdao.getAttach(bno);
	}

	@Override
	public void deleteAttach(String fileName, int bno) {
		// TODO Auto-generated method stub
		dao.deleteAttach(fileName, bno);
	}
	

	

	

	
	
	

		
}
