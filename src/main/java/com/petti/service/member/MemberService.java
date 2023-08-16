package com.petti.service.member;

import java.util.Map;

import com.petti.domain.member.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);
	
	void modify(MemberVO vo);
	
	MemberVO read(String memberId);
	
	void chagePassword(Map<String, String> memberMap);
}
