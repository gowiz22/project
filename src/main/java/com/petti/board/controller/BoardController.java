package com.petti.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petti.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", boardService.getList());
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model) {
		model.addAttribute("vo", boardService.get(bno));
	}
}
