package kr.or.ksmart37.ksmart_springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart37.ksmart_springboot.dto.Member;

@Controller
public class MainController {
	
	@GetMapping("/test7")
	public String test7(Model model) {
		model.addAttribute("title","test7");

		return "thymeleaf/test7";
		
	}
	
	@PostMapping("/test6")
	public String test6(Model model, Member member, @RequestParam(value="memberId", required = false) String memberId) {
		

		System.out.println("test 6 화면에서 입력받은 값 : "+member);
		System.out.println("test 6 화면에서 memberId 입력 받은 값 : "+memberId);
		
		/*
		 * Spring 에서는 아래와 같은 방법으로 리다이렉트를 간단하게 가능하다.
		 * redirect : address
		 * */
		return "redirect:/";
		
	}
	
	@GetMapping("/test6")
	public String test6(Model model) {
		model.addAttribute("title", "test6");
		
		return "thymeleaf/test6";
	}
	
	@GetMapping("/test5")
	public String test5(Model model) {
		
		List<Member> memberList = new ArrayList<Member>();
		Member member = null;
		
		for(int i=1; i<11; i++) {
			if(i%3 == 0) {
				member = new Member("id00"+i, "pw00"+i, "구매자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}else if(i%3 == 1) {
				member = new Member("id00"+i, "pw00"+i, "관리자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}else if(i%3==2) {
				member = new Member("id00"+i, "pw00"+i, "판매자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}
			else {
				member = new Member("id00"+i, "pw00"+i, "사용자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}
			memberList.add(member);
		}   
	    
	    model.addAttribute("memberList", memberList);
		
		model.addAttribute("now", new Date());
		model.addAttribute("price", 1234567890);
		model.addAttribute("title", "test5");
		
		return "thymeleaf/test5";
	}
	
	@RequestMapping(value="/test4", method = RequestMethod.GET)
	public String test4(Model model) {
		
		List<Member> list = new ArrayList<Member>();
		Member member = null;
		
		for(int i=1; i<11; i++) {
			if(i%3 == 0) {
				member = new Member("id00"+i, "pw00"+i, "구매자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}else if(i%3 == 1) {
				member = new Member("id00"+i, "pw00"+i, "관리자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}else if(i%3==2) {
				member = new Member("id00"+i, "pw00"+i, "판매자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}
			else {
				member = new Member("id00"+i, "pw00"+i, "사용자", "홍0"+i,"email"+i+"@ksmart.or.kr");
			}
			list.add(member);
		}   
	    System.out.println(list);
	    model.addAttribute("list", list);
	    
		return "thymeleaf/test4";
	}
// 내가 한 작업 생성자 메소드로 바로 할당시켜서 list 통째로 넣어버림	
//	@RequestMapping(value="/test4", method = RequestMethod.GET)
//	public String test4(Model model) {
//		
//		List<Member> list = new ArrayList<Member>();
//		System.out.println(list);
//		for(int i=0; i<10; i++) {
//			list.add(new Member("id00"+i, "pw00"+i, "level"+i, "홍0"+i,"email"+i+"@ksmart.or.kr"));
//
//		}
//		System.out.println(list);
//	    model.addAttribute("list", list);
//
//		return "thymeleaf/test4";
//	}
	
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
	public String index(Model model) {
		model.addAttribute("title", "maintest");

		return "main";
	}
	
}
