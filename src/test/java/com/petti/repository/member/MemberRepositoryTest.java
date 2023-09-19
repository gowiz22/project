package com.petti.repository.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.member.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class MemberRepositoryTest extends AppTest{

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void test() {
		MemberVO vo = memberRepository.read("admin");
		log.info(vo);
	}

}
