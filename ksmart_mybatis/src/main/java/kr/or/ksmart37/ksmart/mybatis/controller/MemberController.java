package kr.or.ksmart37.ksmart.mybatis.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/sellerList")
	public String sellerList(Model model) {
		List<Member> sellerList = memberService.getSellerList();
		System.out.println(" =============== 판매자별 상품현황==============");
		System.out.println(sellerList);
		System.out.println(" ==========================================");
		model.addAttribute("title", "판매자상품현황");
		model.addAttribute("sellerList", sellerList);
		//return "redirect:memberList";
		return "member/sellerList";
	}
	
	@GetMapping("/removeMember")
	public String removeMember(Model model
			, @RequestParam(name = "memberId", required = false)String memberId			
			, @RequestParam(name="memberLevel", required=false)String memberLevel) {
		model.addAttribute("title", "회원 탈퇴");
		model.addAttribute("memberId", memberId);
		return "member/mDelete";
	}
	
	@PostMapping("/removeMember")
	public String removeMember(@RequestParam(name="memberId", required = false)String memberId
			, @RequestParam(name="memberPw", required=false)String memberPw
			, @RequestParam(name="memberLevel", required=false)String memberLevel) {
		
		System.out.println("회원탈퇴화면에서 입력받은 id ::: " +memberId);
		System.out.println("회원탈퇴화면에서 입력받은 pw ::: " +memberPw);
		System.out.println("회원탈퇴화면에서 입력받은 level ::: " +memberLevel);
		
		//
		String result = memberService.removeMember(memberId, memberPw, memberLevel);
		
		return "redirect:/memberList";
	}

	
	//회원정보 삭제 처리(delete 처리)(내가 처리한거)
	@GetMapping("/deleteMember")
	public String deleteMember(Model model, @RequestParam(name = "memberid", required=false)String memberId) {
		
		System.out.println("삭제할 회원 아이디 :::: " + memberId);
		String result = memberService.deleteMember(memberId);
		
		model.addAttribute("member", result);
		
		
		return "redirect:/memberList";
	}
	
	
	//회원정보 리스트 뿌려주기(업데이트 처리)
	@GetMapping("/modifyMember")
	public String getMemberById(Model model
								,@RequestParam(name = "memberId", required=false)String memberId) {
		
		System.out.println("회원 수정 폼에 보여질 회원 아이디 :::: "+memberId );
		Member member = memberService.getMemberById(memberId);
		System.out.println("화면에서 보여질 정보 ::: "+member);
		model.addAttribute("title", "회원 수정 화면");
		//db에서 검색한 회원 정보
		model.addAttribute("member", member);
		//return "redirect:/memberList";
		return "member/mUpdate";
	}
	
	//Post 방식으로 회원정보 업데이트 처리 get방식과 주소가 다르기 때문에 같은 uri 여도 동작한다.
	@PostMapping("/modifyMember")
	public String modifyMember(Model model, Member member) {
		//화면에서 입력 받은 값
		System.out.println("회원 수정 폼에 보여질 회원 아이디 :::: "+member );
		
		
		//업데이트 처리
		String result = memberService.modifyMember(member);
		
		model.addAttribute("member", result);
		
		//Update 결과 
		System.out.println(result);
		
		return "redirect:/memberList";
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
