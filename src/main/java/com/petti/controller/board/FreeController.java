package com.petti.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petti.domain.Criteria;
import com.petti.domain.Pagination;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.service.free_board.FreeBoardService;

@Controller
@RequestMapping("/free")
public class FreeController {
	
	@Autowired
	private FreeBoardService boardService; 
	
	@GetMapping("/list")
	public String list(Model model, Criteria criteria) {
		model.addAttribute("list", boardService.getList(criteria));
		model.addAttribute("p",new Pagination(criteria, boardService.totalCount(criteria)));
		return "/board/free/free_list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/free/free_get";
	}

	@PostMapping("/register")
	public String register(FreeBoardVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/board/free/free_list";
	}

	@GetMapping("/modify")
	public String modify(Long bno, Model model) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/free/free_modify";
	}
	
	@PostMapping("/modify")
	public String modify(FreeBoardVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/board/free/free_list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/board/free/free_list";
	}
	
}
