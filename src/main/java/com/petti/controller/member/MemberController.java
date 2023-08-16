package com.petti.controller.member;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petti.domain.member.MemberVO;
import com.petti.service.member.MemberService;
import com.petti.service.member.PasswordMisMatchException;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	// 회원 페이지
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping({"/mypage","/mypage/{path}"})
	public String myPage(Model model,Principal principal,
			@PathVariable(required = false) String path) {
		String memberId = principal.getName();
		if(path==null) {
			MemberVO memberVO = memberService.read(memberId);
			model.addAttribute("vo", memberVO);
			return "member/mypage";
		}
		return "member/"+path;
	}
	
	// 관리자 페이지
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/adminPage")
	public String adminPage() {
		return "member/adminPage";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessError";
	}
	
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, 
			Authentication authentication, RedirectAttributes rttr,
			String error, String logout, Model model) {
			String uri = request.getHeader("Referer");
			if(uri!=null && !uri.contains("/login") && !uri.contains("/accessDenied")) {
				request.getSession().setAttribute("prevPage", uri);
			}
			
			if(authentication!=null) {
				rttr.addFlashAttribute("duplicateLogin","이미 로그인 중 입니다.");
				if(uri==null) uri="/";
				return "redirect:"+uri;
			}
			
			if(error!=null) model.addAttribute("error", "로그인 에러");
			if(logout!=null) model.addAttribute("logout", "로그아웃");
			return "member/login";
	}
	

	// 회원 정보수정 처리
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@PostMapping("/member/modify")
	public String modify(MemberVO memberVO, RedirectAttributes rttr) {
		log.info(memberVO);
		memberService.modify(memberVO);
		rttr.addFlashAttribute("result","modify");
		return "redirect:/mypage";
	}
	
	// 비밀번호 변경 처리
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@PostMapping(value = "/mypage/changePwd", produces = "application/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> changePwd(@RequestParam Map<String, String> memberMap) {
		try {
			memberService.chagePassword(memberMap);
		} catch (PasswordMisMatchException e) {
			return new ResponseEntity<String>("비밀번호가 일지하지 않습니다.",HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}

	// 회원 가입
	@GetMapping("/member/join")
	public String joinForm(MemberVO memberVO) {
		return "member/join";
	}
	
	// 회원가입 처리
	@PostMapping("/member/join")
	public String join(MemberVO memberVO, RedirectAttributes rttr) {
		memberService.join(memberVO);
		return "redirect:/";
	}
	
	// 아이디 중복 체크
	@PostMapping("/member/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> idDuplicateCheck(String memberId) {
		MemberVO vo = memberService.read(memberId);
		return vo == null ?
			new	ResponseEntity<>(Boolean.TRUE,HttpStatus.OK)
			: new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
	}
}
