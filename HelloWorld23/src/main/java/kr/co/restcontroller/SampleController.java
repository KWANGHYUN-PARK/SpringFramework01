package kr.co.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.BoardVO;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
	
	@RequestMapping("/hello")
	public String hello(){
		return "Hi, everyone!";
	}
	
	@RequestMapping("/sendvo")
	public BoardVO sendVO(){
		BoardVO vo = new BoardVO(1, "2", "2", "2", null, null, 5) ;
		return vo;
	}
	
	@RequestMapping("/sendlist")
	public List<BoardVO> sendList(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		for(int i=0; i<10; i++) {
			BoardVO vo = new BoardVO(i, "a"+i, "a"+i, "a"+i, null, null, i);
			list.add(vo);
		}
		return list;
	}
	
	
	@RequestMapping("/sendmap")
	public Map<Integer, BoardVO> sendMap(){
		Map<Integer, BoardVO> map = new HashMap<Integer,BoardVO>();
		for(int i=0;i<10;i++) {
			BoardVO vo= new BoardVO(i, "a"+i, "a"+i, "a"+i, null, null, i);
			map.put(i, vo);
			}
		return map;
		
	}
	
	
	//에러난것처럼 보이게 할 수 있음.
	@RequestMapping("/senderror")
	public ResponseEntity<Void> sender(){
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendlistnot")
	public ResponseEntity<List<BoardVO>> sendListNot(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		for(int i=0; i<10; i++) {
			BoardVO vo = new BoardVO(i, "a"+i, "a"+i, "a"+i, null, null, i);
			list.add(vo);
		}
		
		return new ResponseEntity<List<BoardVO>>(list, HttpStatus.NOT_FOUND);
	}
}
