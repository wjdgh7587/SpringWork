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
	@Autowired 
	private MemberMapper memberMapper;
	
	
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
