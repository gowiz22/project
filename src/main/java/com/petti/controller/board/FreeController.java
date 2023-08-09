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
@RequestMapping("/free")
@Log4j
public class FreeController {
	
	@Autowired
	private BoardService boardService; 
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", boardService.getList());
		return "/board/free/list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/free/get";
	}

	@PostMapping("/register")
	public String register(AnnounceVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:/board/free/list";
	}

	@PostMapping("/modify")
	public String modify(AnnounceVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/free/list";
	}
	
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/free/list";
	}
	
}
