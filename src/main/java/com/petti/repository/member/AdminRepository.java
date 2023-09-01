package com.petti.repository.member;

import java.util.List;

import com.petti.domain.member.MemberVO;

public interface AdminRepository {

	public List<MemberVO> searchMember(String memberId);
}
