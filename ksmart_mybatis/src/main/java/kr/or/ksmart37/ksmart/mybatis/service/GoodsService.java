package kr.or.ksmart37.ksmart.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart37.ksmart.mybatis.dto.Goods;
import kr.or.ksmart37.ksmart.mybatis.dto.Member;
import kr.or.ksmart37.ksmart.mybatis.mapper.GoodsMapper;
import kr.or.ksmart37.ksmart.mybatis.mapper.MemberMapper;

@Service
@Transactional // 트랜잭션을 손 쉽게 하게 한다.
public class GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private MemberMapper memberMapper;
	
	
	//판매자 정보 리스트로 받아서 반환
	public List<Goods> getGoodsList(){
				
		return goodsMapper.getGoodsList();
		
	}
	
	public Goods getGoodsByCode2(String goodsCode) {
		
		Goods goods = goodsMapper.getGoodsByCode(goodsCode);
		
		return goods;
	}
	
	
	// 상품코드 받아서 리스트 뽑아오기
	public Goods getGoodsByCode(String goodsCode) {
		// GoodsMapper 에서 코드에 일치하는 상품정보가 단김 객체 받아오기
		Goods goods = goodsMapper.getGoodsByCode(goodsCode);

		// Goods 객체 return
		return goods;
	}

	public String modifyGoods(Goods goods) {
		String modifyCheck = "상품 수정 실패";
		if (goods != null) {
			int result = goodsMapper.modifyGoods(goods);
			if (result > 0) {
				modifyCheck = "회원가입성공";
			}
		}

		return modifyCheck;
	}
	
	//삭제 처리 (아이디 및 비밀번호 비교 후 처리 시작)	
	//@Autowired private MemberMapper memberMapper; 로 Member 테이블 연결

	public String removeGoods1(String goodsCode, String memberId, String memberPw) {
		String result = "상품정보 삭제 실패";
		
		Member member = memberMapper.getMemberById(memberId);
		
		if(member != null && member.getMemberPw() != null && memberPw.equals(member.getMemberPw())) {
			int removeCheck = goodsMapper.removeGoods(goodsCode);
			
			if(removeCheck > 0) result = "상품 삭제 완료";
		}
		
		
		return result;		
	}
	public String removeGoods2(String goodsCode) {

		String deleteCheck = "상품정보 삭제 실패";
		if (goodsCode != null) {
			int result = goodsMapper.removeGoods(goodsCode);
			if (result > 0) {
				deleteCheck = "상품정보 삭제 성공";
			}
		}

		return deleteCheck;
	}


}
