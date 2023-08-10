package com.petti.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.petti.domain.Criteria;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/mainSearch")
	public void mainSearch(Model model, Criteria criteria) {
		model.addAttribute("mainSearchTypes", criteria.getTypes());
		model.addAttribute("mainSearchKeyword", criteria.getKeyword());
	}
}
