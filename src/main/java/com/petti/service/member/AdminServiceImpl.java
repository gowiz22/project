package com.petti.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.Criteria;
import com.petti.domain.member.MemberVO;
import com.petti.repository.member.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;
	
	@Override
	public List<MemberVO> findMemeber(Criteria criteria) {
		return repository.searchMember(criteria);
	}

}
