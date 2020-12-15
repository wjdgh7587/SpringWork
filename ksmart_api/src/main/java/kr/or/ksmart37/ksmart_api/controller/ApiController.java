package kr.or.ksmart37.ksmart_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Rest Controller 사용방법
 * **/
//json 포멧 방식으로 알아서 반환해준다.
@RestController
public class ApiController {
	
	@DeleteMapping("/member")
	public int memberDelete(
			@RequestParam("memberId") String memberId) {
		//Memberber Url로 요청하는데 method는 delete이다.
		
		return 0;
	}
	
	//get방식으로 member url 호출하였다.
	@GetMapping("/member")
	public Map<String, Object> memberRead(
			@RequestParam("memberId") String memberId) {
		
		//회원정보를 검색을 하여, 객체를 리턴을하면, json 방식으로 알아서 리턴이된다.
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	 
}
