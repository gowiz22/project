package com.petti.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petti.domain.AnnounceVO;
import com.petti.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/announce")
@Log4j
public class AnnnounceController {
	
	@Autowired
	private BoardService boardService; 
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", boardService.getList());
		return "/board/announce/list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/announce/get";
	}

	@GetMapping("/register")
	public String register() {
		return "/board/announce/register";
	}

	@PostMapping("/register")
	public String register(AnnounceVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/announce/list";
	}

	@GetMapping("/modify")
	public String modify(Long bno, Model model) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/announce/modify";
	}

	
	@PostMapping("/modify")
	public String modify(AnnounceVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/announce/list";
	}
	
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/announce/list";
	}
	
}
