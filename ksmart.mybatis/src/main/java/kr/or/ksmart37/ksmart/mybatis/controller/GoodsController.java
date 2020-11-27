package kr.or.ksmart37.ksmart.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart37.ksmart.mybatis.dto.Goods;
import kr.or.ksmart37.ksmart.mybatis.dto.Member;
import kr.or.ksmart37.ksmart.mybatis.mapper.GoodsMapper;
import kr.or.ksmart37.ksmart.mybatis.service.GoodsService;

//Goods Controller 따로 제작

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsSerive;

	// 안에 비즈니스 로직(처리)이 존재하지 않다면 아래와 같이 바로 접근해도 상관이없다.
	@Autowired
	private GoodsMapper goodsMapper;

//////////////////////////////              컨트롤러 맵핑 연습(gList에서)             ///////////////////////////////////	
	@GetMapping("/modifyGoods")
	public String modifyGoods(Model model, @RequestParam(name = "goodsCode", required = false) String goodsCode) {
		System.out.println("상품 수정 화면에서 입력받은 값 : " + goodsCode);

		Goods goods = goodsSerive.getGoodsByCode(goodsCode);
		System.out.println("화면에서 보여질 정보 ::: " + goods);

		model.addAttribute("title", "상품수정");
		model.addAttribute("goods", goods);

		// return "redirect:/sellerList";
		return "goods/gUpdate";
	}

	@GetMapping("/removeGoods")
	public String removeGoods(Model model, @RequestParam(name = "goodsCode", required = false) String goodsCode) {
		Goods goods = goodsSerive.getGoodsByCode(goodsCode);
		System.out.println("화면에서 보여질 정보 ::: " + goods);

		model.addAttribute("title", "상품삭제");
		model.addAttribute("goodsCode", goodsCode);
		model.addAttribute("goodsSellerId", goods.getGoodsSellerId());
		return "goods/gDelete";
	}

///////////////////////         ////////////////////////////////////////       ///////////////////////////////////	

	// 상품등록 입력 페이지
	@GetMapping("addGoods")
	public String gInsert(Model model) {

		model.addAttribute("title", "상품등록");

		return "goods/gInsert";

	}

	// 상품등록 처리 페이지
	@PostMapping("addGoods")
	public String gInsert(Goods goods,
			@RequestParam(name = "memberLevel", required = false, defaultValue = "4") int memberLevel) {
		
		System.out.println("상품등록화면에서 입력 받은 값 ::: " + goods);
		System.out.println("상품등록화면에서 입력 받은 값 ::: " + memberLevel);

		if (memberLevel < 3) {
			String result = goodsSerive.addGoods(goods);
			System.out.println("상품 등록 아이디 ::: " + goods.getGoodsSellerId() + "  상품등록정보 ::: " + goods.getGoodsCode() + " || "
					+ goods.getGoodsName() + "===" + result);

			return "redirect:/goodsList";
		} else if (memberLevel == 3) {
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
//		return "redirect:/goodsList";
	}

	// 판매자 전체 조회
	@GetMapping("/goodsList")
	public String getGoodsList(Model model) {

		List<Goods> goodsList = goodsSerive.getGoodsList();

		System.out.println("================상품목록================");
		System.out.println(goodsList);
		System.out.println("======================================");

		model.addAttribute("title", "상품목록");

		// 아래와 같은 방법은 좋은 방법은 아닐 수 도 있다.
		model.addAttribute("goodsList", goodsMapper.getGoodsList());
		return "goods/gList";
	}

	@PostMapping("/sellerRemoveGoods")
	public String removeGoods(Model model, @RequestParam(name = "goodsCode", required = false) String goodsCode,
			@RequestParam(name = "memberId", required = false) String memberId,
			@RequestParam(name = "memberPw", required = false) String memberPw) {

		// 값이 잘 들어왔는지 확인
		System.out.println("상품삭제화면에서 받은 상품코드 value ::: " + goodsCode);
		System.out.println("상품삭제화면에서 받은 회원이름 value ::: " + memberId);
		System.out.println("상품삭제화면에서 받은 회원비밀번호 value ::: " + memberPw);

		String result = goodsSerive.removeGoods1(goodsCode, memberId, memberPw);
		String result2 = goodsSerive.removeGoods2(goodsCode);

		System.out.println("goodsCode :  " + result);

		// model.addAttribute("member", result);

		return "redirect:/goodsList";
	}

	@PostMapping("/sellerModifyGoods")
	// ModelAndView 기존에 나누었던 작업을 한꺼번에 처리함
	public ModelAndView sellerModifyGoods(ModelAndView mav, Goods goods) {
		// 값이 잘 들어왔는지 확인
		System.out.println("판매자별 상품수정 페이지에서 요청 받은 값 >> " + goods);

		String result = goodsSerive.modifyGoods(goods);

		System.out.println(goods.getGoodsCode() + " : " + result);
		// model 객체와 view 논리주소를 한번에 처리할 수 있는 객체
		// ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/goodsList");

		return mav;
	}
}