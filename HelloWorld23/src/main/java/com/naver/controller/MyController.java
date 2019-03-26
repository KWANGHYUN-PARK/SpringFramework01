package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.MemberDTO;

@Controller
public class MyController {

//	1번
	@RequestMapping("/doA")
	public void doA() {
		System.out.println("doA");
		
//		void로 했다는 것은
//		url을 뷰값으로 사용하겠다는 뜻
//		이 상태에서 실행하면 
		
	}
	
//	2번
	@RequestMapping("/doB")
	public void doA(MemberDTO dto) {
		System.out.println(dto);
		
//		http://localhost:8089/doB?id=8&name=ff&age=22
	}
	
	
	
	
//	3번
	@RequestMapping("/doC")
	public void doA(int id, String name, int age) {
		System.out.println(id);
		System.out.println(name);
		System.out.println(age);
		
//		http://localhost:8089/doC?id=100&name=ff&age=22
	}
	
	
	
//	4번
	@RequestMapping("/doD")
	public String doD() {
		System.out.println("doD...................");
		
		
		return "doD";
		
//		http://localhost:8089/doD
	}
	//리턴값 이렇게 쓰면 doD.jsp로 넘어가는...
	
	
	
	
	
//	5번
	@RequestMapping("/doE")
	public String doE(Model model) {
		//객체 바인딩해서 보내고 싶을 때 파라미터에 Model 사용...
		model.addAttribute("test","asdlkfjasdlkfj");
		System.out.println();
		
		return "doE";
//		views 폴더에 doE.jsp 만들기
//		test에 바인딩한 값을 jsp에 ${test}로 호출
//		http://localhost:8089/doE
	}
	
	
//	6번
//	아래의 두개가  하나의 예제
//	http://localhost:8089/doF 입력하면 리다이렉트 때문에 doG로 넘어간다
	@RequestMapping("/doF")
	public String doF() {
		System.out.println("doF()................");
		return "redirect:/doG";
	}
	
	@RequestMapping("/doG")
	public String doG() {
		System.out.println("doG()................");
		return "/doG";
	}
	
	
	
	
//	7번
//	redirect:/ 옵션을 넣으면 다시 컨트롤러로 보내는 뜻이라는 듯. 20190307 카톡단톡에 정보 공유 받은적 있음
//	http://localhost:8089/doH로 테스트하면 리다이렉트 때문에 doI컨트롤러 호출하고 url에 show 관련 정보 함께 출력된다.
	@RequestMapping("/doH")
	public String doH(Model model) {
		model.addAttribute("show", "show 객체에 들어가는 값");
		return "redirect:/doI";
	}
	
	@RequestMapping("/doI")
	public String doH(String show) {
		System.out.println(show);
		return "/doI";
	}
	//위의 두 컨트롤러 http://localhost:8089/doH로 실행하면 doH에서 바인딩한 값이 있지만, doI.jsp에서 ${show} 해도 값이 안나옴
	//모델에 바인딩을 했지만, 리다이렉트 하면 모델에 바인딩 했던 값을 전달하지 못한다.
	
	
	
	
	
	
	
	//8번 리다이렉트한 값을 받을 수 있는 방법은?
	@RequestMapping("/doJ")
	public String doJ(Model model) {
		System.out.println("doJ().......................");
		model.addAttribute("newK", "newK에 바인딩한 값");
		return "redirect:/doK";
	}
	@RequestMapping("/doK")
	public String doJ(String newK, Model model) {
		System.out.println(newK);
		model.addAttribute("newK", newK);
		return "doK";
	}
	
	
	
	
	//9번 @ModelAttribute 활용 예제 @ModelAttribute의 역할 ?! View에 직접 값을 보내고 싶을떄?!	
	//@ModelAttribute 주소창에 바인딩된 값이 노출된다는 문제점 가지고 있어서 Model과는 자주 쓰이지 않음.
	//@RedirectAttributes 더 자주쓰임
	@RequestMapping("/doL")
	public String doL(Model model) {
		model.addAttribute("simple", "simple");
		return "redirect:/doM";
	}
	
	@RequestMapping("doM")
	private String doM(@ModelAttribute("simple") String simple) {
//		System.out.println(simple);
		System.out.println(simple);
		
		return "doM";
	}
	
	
	
	
	//10번   RedirectAttributes예제
//	9번의 방식으로 바인딩한 값을 뷰로 보내면 url에 바인딩된 값이 노출되는 단점 있었는데,
//	이러한 문제 해결하기 위해 사용되는 방법이 아래와 같다
	@RequestMapping("/doO")
	public String doO(RedirectAttributes rtts) {
		
		rtts.addFlashAttribute("rttr", "rttr에 바인딩한 값");
		//RedirectAttributes 에 메소드 아주 여러가지 있음.
		return "redirect:/doP";
	}
	@RequestMapping("/doP")
	public String doP(@ModelAttribute("rttr") String rttr) {
		
		
		return "/doP";// 여기에는 앞에 '/'기호 안넣어도 정상 작동 되는 듯... 
		
//		doP.jsp에서 ${rttr} 입력해서 바인딩된 값을 호출
	}

	
	
	
//	12번
	@RequestMapping("/doQ")
	public @ResponseBody MemberDTO doQ(){
		MemberDTO dto = new MemberDTO(1,"kk",22);
		
		return dto;
	}
	
	
	
	
//	13번
//	http://localhost:8089/doR?num=4
	@RequestMapping("/doR")
	public void doR(@RequestParam int num) {//스프링프레임워크 4버전 나오면서 안써도 되게 되었다는데 뭐가?
		System.out.println(num);
	}
	
	
//	http://localhost:8089/doR1?num=4
	@RequestMapping("/doR1")
	public void doR1(int num) {//안써도 된다니까 안쓰고 되는지 안되는지 자체 테스트
		System.out.println(num);
	}
	
	
	
	
//	14번
	@RequestMapping("/doS")
	public ModelAndView doS() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("str", "This is for Sindorim station");
		mav.setViewName("sorry");//view 이름 바꿈
		return mav;
	}
	
	
	
	
	
}
