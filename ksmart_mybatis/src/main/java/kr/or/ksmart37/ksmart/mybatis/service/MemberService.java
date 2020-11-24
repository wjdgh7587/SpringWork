package kr.or.ksmart37.ksmart.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart37.ksmart.mybatis.dto.Member;
import kr.or.ksmart37.ksmart.mybatis.mapper.MemberMapper;

@Service
@Transactional//트랜잭션 손쉽게 함
public class MemberService {
	
	//	DI
	//의존성 주입(new 와 동일하다.)
	

	 //생성자를 통한 autowired 예제
	/*
	  private MemberMapper memberMapper;
	 	
	  @Autowired 
	  public MemberService(MemberMapper memberMapper) {
		  this.memberMapper = memberMapper;
	  }
	*/
	//필드를 통한 autowired 예제
	@Autowired
	private MemberMapper memberMapper;
	
	
	//removeDelete 처리하기
	public String removeMember(String memberId, String memberPw, String memberLevel) {
		String result = "회원탈퇴 실패";
		//id member 테이블 조회하고 조회한 결과 값 중 비밀번호와 화면에서 입력받은 
		//비밀번호가 일치하면 삭제 처리
		
		Member member = memberMapper.getMemberById(memberId);
		
		if(member != null && member.getMemberPw() != null && memberPw.equals(member.getMemberPw())) {
			
			int removeCheck = memberMapper.removeLoginById(memberId);
			
			if("판매자".equals(memberLevel)) removeCheck += memberMapper.removeGoodsById(memberId);
			
			if("구매자".equals(memberLevel)) removeCheck += memberMapper.removeOrderById(memberId);
			
			removeCheck += memberMapper.removeMemberById(memberId);
			
			if(removeCheck > 0 ) result = "회원삭제 성공";

		}
		
		return result;
	}
	
	//Delete 처리하기(Myself first)	
	public String deleteMember(String memberId) {
		
		String deleteCheck = "회원정보 삭제 실패";
		if(memberId != null) {
			int result = memberMapper.deleteMember(memberId);
			if(result > 0) {
				deleteCheck = "회원정보 삭제 성공";
			}
		}
	
		
		return deleteCheck;
	}
	
	
	//Udpate 리스트 받아오기
	
	public Member getMemberById(String memberId) {
		Member member = memberMapper.getMemberById(memberId);
		
		return member;
	}
	//Update 처리
	public String modifyMember(Member member) {
		String modifyCheck = "회원정보 수정 실패";
		if(member != null) {
			int result = memberMapper.modifyMember(member);
			if(result > 0) {
				modifyCheck = "회원가입성공";
			}
		}
		
		return modifyCheck;
	}
	
	//회원가입 성공 유무 판단 및 addMember 서비스 호출
	public String addMember(Member member) {
		String insertCheck = "회원가입 실패";
		if(member != null) {
			int result = memberMapper.addMember(member);
			if(result > 0) {
				insertCheck = "회원가입성공";
			}
		}
	
		return "insertCheck";
	}
	
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		int listSize = memberList.size();
		
		/*과제*/
		/*
		 * 권한 번호들을 관리자, 판매자, 사용자 등을 판별하여 memberList에 다시 세팅해서 반환하여, 화면에 사용자 권한별로 표시되게 하기
		 * 
		 * */
		
		for(int i=0; i<listSize; i++) {
			if(memberList.get(i).getMemberLevel().equals("1")) {
				memberList.get(i).setMemberLevel("관리자");
			}else if(memberList.get(i).getMemberLevel().equals("2")) {
				memberList.get(i).setMemberLevel("판매자");
			}else if(memberList.get(i).getMemberLevel().equals("3")) {
				memberList.get(i).setMemberLevel("사용자");
			}
			
		}
	
		return memberList;
		
	}


		
}
