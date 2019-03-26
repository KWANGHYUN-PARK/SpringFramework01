package kr.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dao.Criteria;
import kr.co.domain.PageMaker;
import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Inject
	private ReplyService service;

	@RequestMapping(value = "/checkid/{inputID}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkId(@PathVariable("inputID") int inputID) {
		ResponseEntity<Boolean> entity = null;

		/* boolean ok = service.checkId(); */

		if (inputID == 1) {
			entity = new ResponseEntity<Boolean>(false, HttpStatus.OK);

		} else {
			entity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return entity;
	}

	@RequestMapping(value = "/checkid2/{inputID2}", method = RequestMethod.GET)
	public ResponseEntity<String> checkId2(@PathVariable("inputID2") int inputID2) {
		ResponseEntity<String> entity = null;

		/* boolean ok = service.checkId(); */

		if (inputID2 == 1) {
			entity = new ResponseEntity<String>("사용불가", HttpStatus.OK);

		} else {
			entity = new ResponseEntity<String>("사용가능", HttpStatus.OK);
		}
		return entity;
	}

	/*
	 * @RequestMapping(value="/checkid3/{inputID3}",method=RequestMethod.GET) public
	 * ResponseEntity<Map<String,Object>> checkId3(@PathVariable("inputID3") int
	 * inputID3){ ResponseEntity<Map<String,Object>> entity=null;
	 * 
	 * boolean ok = service.checkId(); Map<String,Object> map = new HashMap<>();
	 * if(inputID3==1) { msg="사용불가";
	 * 
	 * }else { msg="사용가능"; } map.put("msg", msg); entity=new
	 * ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); return entity; }
	 */

	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> list(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			List<ReplyVO> list = service.list(bno, page);
			int amount = service.getAmount(bno);
			PageMaker pm = new PageMaker();
			Criteria cri = new Criteria();
			cri.setPage(page);

			pm.setCri(cri);
			pm.setAmount(amount);
			// cri 먼저 셋하고 셋어마운트 순서 이렇게되야함

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("pm", pm);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "/{rno}", method = RequestMethod.PUT) // update 할 때 put과 patch모두 사용 가능, 동시에 둘다 사용할 수도 있음
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo) {

		ResponseEntity<String> entity = null;

		try {
			vo.setRno(rno);
			service.update(vo);
			entity = new ResponseEntity<String>("UPDATE_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/{bno}", method = RequestMethod.GET)
	// MVC 핸들러
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno) {
		ResponseEntity<List<ReplyVO>> entity = null;
		// ResponseEntity 타입은 개발자가 직접 결과 데이터 + HTTP의 상태 코드를 제어할 수 있는 클래스

		try {
			List<ReplyVO> list = service.list(bno);
			entity = new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody ReplyVO vo) {

		System.out.println(vo);
		ResponseEntity<String> entity = null;

		try {
			service.insert(vo);
			entity = new ResponseEntity<String>("INSERT_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
		ResponseEntity<String> entity = null;
		try {
			service.delete(rno);
			entity = new ResponseEntity<String>("DELETE_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
