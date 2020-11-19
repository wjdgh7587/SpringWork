package kr.or.ksmart37.ksmart.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ksmart37.ksmart.mybatis.dto.Member;

@Mapper
public interface MemberMapper {
	public List<Member> getMemberList();
}
