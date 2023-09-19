package com.petti.controller.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.member.AuthVO;
import com.petti.service.member.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")		
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	public String searchMember(Model model, @RequestParam(required = false) String memberId) {
			if(memberId != null) {
				model.addAttribute("list", adminService.findMemeber(memberId));
				model.addAttribute("memberId",memberId);
			} else {
				memberId = "";
				model.addAttribute("list", adminService.findMemeber(memberId));
			}
		return "/admin/adminPage";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/changeRole")
	public String giveRoll(AuthVO vo) {
		adminService.updateAuth(vo);
		return "redirect:/admin";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@GetMapping("/board")
	public String boardManage(Model model) {
		model.addAttribute("category", adminService.categoryList());
		return "/admin/board_manage";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/renameCategory")
	public String renameCategory(CategoryVO vo) {
		adminService.renameCategory(vo);
		return "redirect:/admin/board";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/newCategory")
	public String newCategory(String kind) {
		adminService.newCategory(kind);
		return "redirect:/admin/board";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/removeCategory")
	public String removeCategory(Long cno) {
		adminService.deleteCategory(cno);
		return "redirect:/admin/board";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/removeBoard")
	public String removeBoard(Long cno) {
		adminService.deleteBoard(cno);
		return "redirect:/admin/board";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/relocateBoard")
	public String relocateBoard(@Param("newCno") Long newCno, @Param("oldCno") Long oldCno) {
			adminService.updateCategory(oldCno, newCno);
		return "redirect:/admin/board";
	}
	
}
