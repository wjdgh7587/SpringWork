package kr.or.ksmart37.ksmart_api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//ajax 호출 및 parsing 작업을 할 것이다.
//ajax를 다른 url에서 호출할 경우를 차단해야한다. 

@Controller
public class ApiController2 {

	@GetMapping("/apiCall")
	public String apiCall(Model model) {
		model.addAttribute("title", "Api call");
		return "apiCall";
	}
//	@GetMapping("/apiCall2")
//	public String apiCall2(Model model) {
//		model.addAttribute("title", "Api call2");
//		return "apiCall";
//	}

	// 맵핑 여러가지 하고 싶으면 배열로 묶어서 하면 된다.
	// @ResponseBody는 응답을 내가 스스로 자체적으로 응답하겠다.
	// return을 "{}" 해야한다.
	@GetMapping(value = "/apiResult", produces = "application/json")
	public @ResponseBody List<Map<String, String>> apiCall2(Model model, 
			@RequestParam("zone") String zone) {
		
		model.addAttribute("title", "Api call json");

		String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp";
		
		List<Map<String, String>> wList = new ArrayList<Map<String, String>>();

		try {
			Document jsoup = Jsoup.connect(url).data("zone", zone).get();
			
			//실제 응답이 되는지 확인
			//System.out.println(jsoup.toString());
			Elements els = jsoup.select("data");
			
			for(Element el : els) {
				Map<String, String> map = new HashMap<String,String>();
				String hour =  el.select("hour").text();
				String wfKor = el.select("wfKor").text();
				
				map.put("hour", hour);
				map.put("wfKor", wfKor);
				
				wList.add(map);
				System.out.println(hour + " -- "+wfKor);
				
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return wList;
	}
	@GetMapping(value = "/json", produces = "application/json")
	public @ResponseBody Map<String, String> json(){
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("test1", "1");
		map.put("test2", "2");
		map.put("test3", "3");
		map.put("test4", "4");

	
		return map;
	}
	
	@GetMapping("/apiCall3")
	public String apiCall3() {
		URL url;
		try {
			url = new URL("http://localhost/json");
			URLConnection urlc = url.openConnection();
			InputStream in = urlc.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			
			String temp;
			String jsonStr = "";
			while((temp = br.readLine()) != null) {
				System.out.println(temp);
				jsonStr += temp;
			}
			System.out.println(jsonStr);
			
			System.out.println(urlc.toString());
			
			JSONParser json = new JSONParser(jsonStr);
			//json.parseObject();
			//한번 호출하면 empty가 되어서 될 수가 없어서 안된다. 한번 담기면 끝? 이렇게 이해하면 될듯 하다.
			LinkedHashMap<String, Object> map = json.parseObject();
			System.out.println(map.toString());
			System.out.println(map.get("test1") + " << "+"test1");
			System.out.println(map.get("test2") + " << "+"test2");
			System.out.println(map.get("test3") + " << "+"test3");
			System.out.println(map.get("test4") + " << "+"test4");
			
			//System.out.println(json.parseObject());
		} catch (IOException e) {		
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}	
		
		return "apiCall";
	}

}
