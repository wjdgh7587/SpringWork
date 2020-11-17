package kr.or.ksmart37.ksmart_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ksmart37.ksmart_springboot.dto.Member;

@Controller
public class MainController {
	@GetMapping("/test3")
	public String test3(Model model) {
		Member m = new Member();
		m.setMemberId("id001");
		m.setMemberPw("pw001");
		m.setMemberLevel("관리자");
		m.setMemberName("홍01");
		m.setMemberEmail("홍01@ksmart.or.kr");
		model.addAttribute("member",m);
		return "thymeleaf/test3";
		
	}
	
	@GetMapping("/test2")
	public String test2(Model model) {
		Member m = new Member();
		m.setMemberId("id001");
		m.setMemberPw("pw001");
		m.setMemberLevel("관리자");
		m.setMemberName("홍01");
		m.setMemberEmail("홍01@ksmart.or.kr");
		model.addAttribute("member",m);
		model.addAttribute("title", "ksamrt_title_test2");
		model.addAttribute("test", "ksamrt_thymeleaf_test2");
		return "thymeleaf/test2";
	}
	
	/*
	 * 모델의 역할
	 * Model = 화면에 전달할 정보를 담아준다.
	 * 커맨드객체 : 화면에서 전달하는 정보를 바인딩하는 객체
	 * 
	 * */

	@GetMapping("/test1")
	public String test1(Model model) {
		model.addAttribute("title", "ksamrt_title_test1");
		model.addAttribute("test", "ksamrt_thymeleaf_test1");
	
		return "thymeleaf/test1";	
	}
	
	/*
	 * getMapping get방식 url 요청
	 * index메소드 실행
	 * return -> 논리명, 파일명(html)
	 * */
	@GetMapping("/")
	public String index() {
		
		return "main";
	}
	
}
