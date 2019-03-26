package kr.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession session;//이름은 동일하게 session이지만 jsp의 세션과는 다르다
	
	private final String NS="kr.co.mapper.board";//매퍼 네임스페이스에 있는거 복사해오기
	
	@Override
	public List<BoardVO> list() {
		
		return session.selectList(NS+".list");
	}

	@Override
	public void insert(BoardVO vo) {
		// TODO Auto-generated method stub
		Integer bno=createBno();
		if(bno==null) {
			bno=1;
		}else {
			bno++;
		}
		vo.setBno(bno);
		session.insert(NS+".insert",vo);
	}

	private Integer createBno() {
		
		return session.selectOne(NS+".createBno");
	}

	@Override
	public BoardVO read(int bno) {
		
		return session.selectOne(NS+".read",bno);
	}

	@Override
	public BoardVO updateUI(int bno) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".updateUI",bno);
	}

	@Override
	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		session.update(NS+".update", vo);
	}

	@Override
	public void delete(BoardVO vo) {
		// TODO Auto-generated method stub
		session.delete(NS+".delete",vo);
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) {
		
		
		RowBounds rb = new RowBounds(cri.getStartNum()-1, cri.getPerPage()); /* 1번부터 시작해서 10개 */
		return session.selectList(NS+".list", 0, rb);
		//파라미터값 cri.getStartNum()-1로 하지 않으면 맨 마지막에 쓴 글이 표시되지 않는다.
	}

	@Override
	public int getAmount() {
		
		return session.selectOne(NS+".getAmount");
	}

	@Override
	public List<BoardVO> search(SearchCriteria cri) {
		// TODO Auto-generated method stub
		RowBounds rb=new RowBounds(cri.getStartNum()-1, cri.getPerPage());
		return session.selectList(NS+".search", cri, rb);
	}
	
	@Override
	public int getSearchAmount(SearchCriteria cri) {
		return session.selectOne(NS+".getSearchAmount",cri);
	}

	@Override
	public void delete(int bno) {
		// TODO Auto-generated method stub
		session.delete(NS+".delete",bno);
	}
	
	@Override
	public void updateViewCnt(int bno) {
		session.update(NS+".jpdateViewCnt",bno);
		
	}

	@Override
	public void incrViewCnt(int bno) {
		// TODO Auto-generated method stub
		session.update(NS+".incrViewCnt",bno);
	}

	@Override
	public void addAttach(String fullName, int bno) {
		// TODO Auto-generated method stub
		int id = getId();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("fullName",	fullName);
		map.put("bno",	bno);
		map.put("id", id);
		session.insert(NS+".addAttach",map);
	}
	
	private int getId() {
		Integer id=session.selectOne(NS+".getId");
		if(id==null) {
			id=0;
		}
		return ++id;
	}

	@Override
	public void deleteAttach(String fileName, int bno) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileName", fileName);
		map.put("bno",bno);
		session.delete(NS+".deleteAttach", map);
	}

	@Override
	public void deleteAllAttach(int bno) {
		// TODO Auto-generated method stub
		session.delete(NS+".deleteAllAttach", bno);
	}


}
