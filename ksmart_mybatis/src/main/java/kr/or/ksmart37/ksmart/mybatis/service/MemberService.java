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
	
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		
		return memberList;
		
	}
		
}
