package com.petti.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.petti.service.member.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	public String searchMember(Model model, @RequestParam(required = false) String memberId) {
		if(memberId != "") {
			model.addAttribute("list", adminService.findMemeber(memberId));
			model.addAttribute("memberId",memberId);
		} else {
			model.addAttribute("list", "아이디를 입력해주세요");
		}
		return "adminPage";
	}
}
