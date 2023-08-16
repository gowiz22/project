package com.petti.repository.member;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.member.MemberVO;

public interface MemberRepository {

	MemberVO read(String memberId);
	
	void insert(MemberVO vo);
	
	void update(MemberVO vo);
	
	MemberVO selectById(String memberId);
	
	void updatePassword(
			@Param("memberId") String memberId,
			@Param("memberPwd") String memberPwd);
}
