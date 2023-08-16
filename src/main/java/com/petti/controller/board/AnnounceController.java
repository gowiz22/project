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
import com.petti.domain.board.AnnounceVO;
import com.petti.service.board.AnnoBoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/announce")
@Log4j
public class AnnounceController {
	
	@Autowired
	private AnnoBoardService boardService; 
	
	@GetMapping("/list")
	public String list(Model model, Criteria criteria) {
		model.addAttribute("list", boardService.getList(criteria));
		model.addAttribute("p", new Pagination(criteria, boardService.totalCount(criteria)));
		return "/board/announce/list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model, Criteria criteria) {
		model.addAttribute("board", boardService.get(bno));
		return "/board/announce/get";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/register")
	public String register() {
		return "/board/announce/register";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/register")
	public String register(AnnounceVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/announce/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public String modify(Long bno, Model model, Criteria criteria, Authentication auth) throws AccessDeniedException {
		AnnounceVO vo = boardService.get(bno);
		String username  = auth.getName(); // 인증된 사용자 계정
		if(!vo.getWriter().equals(username) && // 글 작성자
			!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {  // 관리자
			throw new AccessDeniedException("Access denied");
		}
		model.addAttribute("board", boardService.get(bno));
		return "/board/announce/modify";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify")
	public String modify(AnnounceVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/announce/list";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username== #writer or hasRole('ROLE_ADMIN')")	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/announce/list";
	}
	
}
