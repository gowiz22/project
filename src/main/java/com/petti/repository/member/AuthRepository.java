package com.petti.repository.member;

import com.petti.domain.member.AuthVO;

public interface AuthRepository {

	void insert(AuthVO vo);
	
	void changeRole(AuthVO vo);
}
