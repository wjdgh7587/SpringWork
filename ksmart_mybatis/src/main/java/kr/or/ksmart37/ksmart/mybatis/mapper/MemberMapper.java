package kr.or.ksmart37.ksmart.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ksmart37.ksmart.mybatis.dto.Member;

@Mapper
public interface MemberMapper {
	List<Member> getSellerList();
	
	//remove 처리 한거
	int removeLoginById(String memberId);
	int removeOrderById(String memberId);
	int removeGoodsById(String memberId);
	int removeMemberById(String memberId);

	public Member removeMember(String memberId, String memberPw, String memberLevel);
	
	//Delete 처리
	public int deleteMember(String memberId);
	
	//Update 처리
	public int modifyMember(Member member);
	
	//단일행을 Member 로 처리 (단일행)
	public Member getMemberById(String memberId);
	
	
	//MemberList (select query 문으로 조회) (다중행)
	public List<Member> getMemberList();
	
	
	//MemberInsert 회원가입 (Insert)
	public int addMember(Member member);
	//만약 하나만 받는다고 한다면, argument를 String memberId 이런식으로 받을 수 있다.
	//단 우리는 모든 값을 받아야 하기 때문에 dto 객체를 통으로 넘긴다.

	
	
}
