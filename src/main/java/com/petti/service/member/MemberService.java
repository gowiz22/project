package com.petti.service.member;

import java.util.Map;

import com.petti.domain.member.MemberVO;

public interface MemberService {
	
	boolean join(MemberVO vo);
	
	void modify(MemberVO vo);
	
	MemberVO read(String memberId);
	
	void changePassword(Map<String, String> memberMap);
}
