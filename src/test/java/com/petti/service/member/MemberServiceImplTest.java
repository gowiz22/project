package com.petti.service.member;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;
import com.petti.repository.member.AuthRepository;

public class MemberServiceImplTest extends AppTest{

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Test
	@Ignore
	public void test() {
		MemberVO vo = new MemberVO(); 
		vo.setMemberId("admin");
		vo.setMemberPwd("1234");
		vo.setMemberName("관리자");
		vo.setEmail("admin@test.com");
		memberService.join(vo);
	}

	@Test
	@Ignore
	public void test2() {
		AuthVO vo = new AuthVO("admin", "ROLE_ADMIN");
		authRepository.insert(vo);
	}
	
	@Test
//	@Ignore
	public void test3() {
		MemberVO vo = new MemberVO(); 
		vo.setMemberId("cooicooi");
		vo.setMemberPwd("1234");
		vo.setMemberName("cooi");
		vo.setEmail("cooicooi@test.com");
		memberService.join(vo);
	}
}
