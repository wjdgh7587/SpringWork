package kr.or.ksmart37.ksmart.mybatis.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart37.ksmart.mybatis.dto.Member;
import kr.or.ksmart37.ksmart.mybatis.mapper.MemberMapper;
import kr.or.ksmart37.ksmart.mybatis.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired 
	private MemberService memberService;
	
	@PostConstruct
	//	-- 단순 객체 확인용 --
	//	미리 한번 객체를 생성함으로서 계속 주소 요청등 비즈니스 요청을 계속해서 수행하는 것이다.
	public void initialize() {
		System.out.println("###########################################################");
		System.out.println("MemberController 객체 생성");
		System.out.println("###########################################################");
	}
	
	//값을 받아서 console에 출력시켜보기
	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public String addMember(Member member, @RequestParam(name = "memberId", required=false) String memberId) {
		System.out.println("회원가입 화면에서 입력 받은 값 ::: "+member);
		//System.out.println(memberId); // 값 하나만 받았을 경우
		String result = memberService.addMember(member);
		System.out.println(result);
		return "redirect:/memberList";
	}
	
	@GetMapping("/addMember")
	public String memberJoin(Model model) {
		model.addAttribute("title", "회원 가입");
		return "member/mInsert";
	}
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		List<Member> memberList = memberService.getMemberList();
		System.out.println(memberList+" : Checking memberList");
		model.addAttribute("title","회원 목록");
		model.addAttribute("memberList", memberList);
		
		return "member/mList";
	}
}
