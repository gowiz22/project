package com.petti.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petti.domain.Criteria;
import com.petti.service.member.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("")
	public void searchMember(Model model, Criteria criteria) {
		model.addAttribute("list", adminService.findMemeber(criteria));
	}
}
