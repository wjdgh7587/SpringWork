package kr.or.ksmart37.ksmart.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ksmart37.ksmart.mybatis.dto.Member;

@Mapper
public interface MemberMapper {
	//MemberList (select query 문으로 조회)
	public List<Member> getMemberList();
	
	//MemberInsert 회원가입 (Insert)
	public int addMember(Member member);
	//만약 하나만 받는다고 한다면, argument를 String memberId 이런식으로 받을 수 있다.
	//단 우리는 모든 값을 받아야 하기 때문에 dto 객체를 통으로 넘긴다.
}
