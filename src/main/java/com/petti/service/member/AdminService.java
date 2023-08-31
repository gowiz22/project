package com.petti.service.member;

import java.util.List;

import com.petti.domain.Criteria;
import com.petti.domain.member.MemberVO;

public interface AdminService {
	public List<MemberVO> findMemeber(Criteria criteria);
}
