package kr.or.ksmart37.thymeleaf_layout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(Model model) {
		return "main";
	}
	@GetMapping("/calendar")
	public String calendar(Model model) {
		return "calendar";
	}
}
