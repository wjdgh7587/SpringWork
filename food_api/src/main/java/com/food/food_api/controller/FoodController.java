package com.food.food_api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {
	@GetMapping("/")
	public String main() {
		return "foodApi";
	}
	@GetMapping(value = "/foodApiResult", produces = "application/json")
	public @ResponseBody List<Map<String, String>> foodApi(Model model
			,@RequestParam("food") String food) throws Exception {
		//List<Map<String, String>>
		// 인증키
		// String keyId = "910606a0c65e48fb9339";
		// url 기본
		// String url =
		// "http://openapi.foodsafetykorea.go.kr/api/keyId/serviceId/dataType/startIdx/endIdx";

		// url 값 인자가 필요할 경우
		// http://openapi.foodsafetykorea.go.kr/api/인증키/서비스명/요청파일타입/요청시작위치/요청종료위치/변수명=값&변수명=값2
		// e.g) http://openapi.foodsafetykorea.go.kr/api/sample/I2790/xml/1/5/DESC_KOR=값
		// &RESEARCH_YEAR=값 &MAKER_NAME=값

		// 서비스 아이디 키값
		// 식품영양성분 : I2790
		// 식품레시피 : COOKRCP01
		// 건강기능식품 : I0760

		// 인증키
		String keyId = "910606a0c65e48fb9339";
		String serviceId = "I2790";
		// 데이터타입
		String dataType = "json";
		//String datatype = "json";
		// index Start, End
		//String startIdx = "1";
		int startIdx = 1;
		//String endIdx = "5";
		int endIdx = 1;

		String url = "http://openapi.foodsafetykorea.go.kr/api/" + keyId + "/" + serviceId + "/"+dataType+"/"
				+ startIdx + "/" + endIdx+"/DESC_KOR="+food;
	///DESC_KOR="+food
		//List<Map<String, String>> foodList = new ArrayList<Map<String, String>>();
		System.out.println(url);

		try {
			
//			Document doc = Jsoup.connect(url).header("content-type", "application/json;charset=UTF-8")
//					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//					 .header("accept-encoding", "gzip, deflate, br")
//					 .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
//					 .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
//					 .ignoreContentType(true).get();
			
			URL url1 = new URL(url);
            
            String line = "";
            String result = "";
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url1.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
                //System.out.println(line);                
            }  
			
            System.out.println(">>>"+result);
            
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(result);    
			JSONObject obj2 = (JSONObject) obj.get("I2790");    
	        JSONArray parse_listArr = (JSONArray) obj2.get("row");
	        System.out.println(parse_listArr);
	        
			/*
			 * for(int i=0;i<parse_listArr.size();i++){ JSONObject temp = (JSONObject)
			 * parse_listArr.get(i); }
			 */

		


					
			//ignoreContentType(true)
			//System.out.println(jsoup.toString());
			
			
			
			
			
			
			//아직 설정하지 말것
//			Elements els = jsoup.select("data");
//			
//			for(Element el : els) {
//				Map<String, String> map = new HashMap<String,String>();
//				
//				String foodName = el.select("DESC_KOR").text();
//				
//				//String Food = ;
//				map.put("Food", foodName);
//				foodList.add(map);
//				System.out.println	(foodList);
			//}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
