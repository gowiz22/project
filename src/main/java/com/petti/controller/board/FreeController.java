package com.petti.controller.board;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		model.addAttribute("category", boardService.category());
		return "/board/free/free_list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model, Criteria criteria) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/free/free_get";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("category", boardService.category());
		return "/board/free/free_register";
	}
	
	@PreAuthorize("isAuthenticated()")	
	@PostMapping("/register")
	public String register(FreeBoardVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/free/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public String modify(Long bno, Model model, Criteria criteria, Authentication auth) throws AccessDeniedException {
		FreeBoardVO vo = boardService.get(bno);
		String username = auth.getName();
		if(!vo.getWriter().equals(username) &&
				!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new AccessDeniedException("Access denied");
		}
		model.addAttribute("board", boardService.get(bno));
		return "/board/free/free_modify";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify")
	public String modify(FreeBoardVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/free/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/free/list";
	}
	
}
