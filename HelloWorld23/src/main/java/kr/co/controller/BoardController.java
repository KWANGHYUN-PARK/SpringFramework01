package kr.co.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dao.Criteria;
import kr.co.dao.SearchCriteria;
import kr.co.domain.BoardVO;
import kr.co.domain.PageMaker;
import kr.co.service.BoardService;
import kr.co.utils.MediaUtils;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping(value="/deletefile",method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, int bno){
		
		
		service.deleteAttach(fileName,bno);
		
		
		ResponseEntity<String> entity = null;
			
	      fileName = fileName.replace('/', File.separatorChar);
	      
	      try {
	         
	         

	         String formatType = fileName.substring(fileName.lastIndexOf(".") + 1);
	         MediaType mType = MediaUtils.getMediaType(formatType);
	         if (mType != null) {
	            String prefix = fileName.substring(0, 12);
	            String suffix = fileName.substring(14);
	            File f1 = new File(uploadPath + prefix + suffix);
	            f1.delete();
	         }
	         
	         
	         File f2 = new File(uploadPath + fileName);
	         f2.delete();
	         
	         entity = new ResponseEntity<String>(HttpStatus.OK);
	      } catch (Exception e) {
	         e.printStackTrace();
	         entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	      }

	      return entity;
	}

	@RequestMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") Integer bno){
		return service.getAttach(bno);
	}
	
	
//	@RequestMapping("/list")
//	public String list (Model model) {
//		List<BoardVO> list = service.list();
//		model.addAttribute("list",list);
//		
//		return"board/list";
//	}
	@RequestMapping("/list")
	public String list(Criteria cri, Model model) {
		List<BoardVO> list = service.listPage(cri);
		int amount = service.getAmount();

		PageMaker pm = new PageMaker(amount, cri);

		model.addAttribute("pm", pm);
		model.addAttribute("list", list);

		return "board/list";
	}

	@RequestMapping(value = "/insertui", method = RequestMethod.GET)
	public String insertui() {

		return "board/insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {
		service.insert(vo);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int bno, Criteria cri, Model model) {
		BoardVO vo = service.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri);
	}

	@RequestMapping(value = "/updateui", method = RequestMethod.GET)
	public void updateui(int bno, Model model, @ModelAttribute("cri") Criteria cri) {

		BoardVO vo = service.updateUI(bno);
		model.addAttribute("vo", vo);
	}

	@RequestMapping("/update")
	public String update(BoardVO vo, Criteria cri, Model model) {
		service.update(vo);

		/* 수정하는 글이 몇 페이지에 있던 글인지 받아온 데이터를 바인딩 */
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPage", cri.getPerPage());
		return "redirect:/board/list";
	}

	/*
	 * @RequestMapping("/delete") public String delete(BoardVO vo, Criteria cri,
	 * Model model) { service.delete(vo);
	 * 
	 * 수정하는 글이 몇 페이지에 있던 글인지 받아온 데이터를 바인딩 model.addAttribute("page", cri.getPage());
	 * model.addAttribute("perPage", cri.getPerPage()); return
	 * "redirect:/board/list"; }
	 */
	@RequestMapping("/delete")
	public String delete(int bno, Criteria cri, Model model) {
		service.delete(bno);
		
		/* 수정하는 글이 몇 페이지에 있던 글인지 받아온 데이터를 바인딩 */
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPage", cri.getPerPage());
		return "redirect:/board/list";
	}

	@RequestMapping("/listpage")
	public String listPage(Criteria cri, Model model) {
		int page = 1;
		List<BoardVO> list = service.listPage(cri);
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping("/search")
	public void search(SearchCriteria cri, String keyword, Model model) {
		List<BoardVO> list = service.search(cri);
		model.addAttribute("list", list);

		int amount = service.getSearchAmount(cri);
		PageMaker pm = new PageMaker(amount, cri);
		model.addAttribute("pm", pm);
		pm.setCri(cri);
	}

	@RequestMapping("/sread")
	public void sRead(int bno, SearchCriteria cri, Model model) {
		BoardVO vo = service.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri);
	}

	@RequestMapping(value = "/sdelete", method = RequestMethod.POST)
	public String sDelete(int bno, SearchCriteria cri, RedirectAttributes rttr) {
		service.delete(bno);

		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPage", cri.getPerPage());
		return "redirect:/board/search";
	}

	@RequestMapping(value = "supdateui", method = RequestMethod.GET)
	public void sUpdateUI(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) {
		BoardVO vo = service.updateUI(bno);
		model.addAttribute("vo", vo);

	}

	@RequestMapping("/supdate")
	public String update(BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) {
		service.update(vo);

		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPage", cri.getPerPage());
		return "redirect:/board/search";
	}

	/*
	 * get방식이면 모델로 바인딩할 수 있지만 post방식이면 RedirectAttributes로 값을 보내야 한다. 디스패쳐방식,
	 * 리다이렉트방식????
	 */
}
