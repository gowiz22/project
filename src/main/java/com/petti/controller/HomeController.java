package com.petti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.petti.domain.Criteria;
import com.petti.service.board.AnnoBoardService;
import com.petti.service.free_board.FreeBoardService;
import com.petti.service.product_board.ProductBoardService;

@Controller
public class HomeController {
	
	@Autowired
	private AnnoBoardService annoBoardService;
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private ProductBoardService productBoardService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/mainSearch")
	public void mainSearch(Model model, Criteria criteria) {
		model.addAttribute("criteria", criteria);
		model.addAttribute("result_anno", annoBoardService.getList(criteria));
		model.addAttribute("result_free", freeBoardService.getList(criteria));
		model.addAttribute("result_product", productBoardService.getList(criteria));
		model.addAttribute("Thumbnail", productBoardService.getThumbnail());

	}
}
