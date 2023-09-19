package com.petti.repository.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.member.MemberVO;

public interface AdminRepository {

	public List<MemberVO> searchMember(String memberId);
	
	// 게시물 카테고리 변경
	void changeCategory(@Param("oldCno") Long oldCno, @Param("newCno") Long newCno);
}
