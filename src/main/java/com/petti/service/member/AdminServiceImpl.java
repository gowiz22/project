package com.petti.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;
import com.petti.repository.free_board.FreeCategoryRepository;
import com.petti.repository.member.AdminRepository;
import com.petti.repository.member.AuthRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private FreeCategoryRepository categoryRepository;
	
	@Override
	public List<MemberVO> findMemeber(String memberId) {
		return repository.searchMember(memberId);
	}

	@Override
	public void updateAuth(AuthVO vo) {
		authRepository.changeRole(vo);
	}

	@Override
	public List<CategoryVO> categoryList() {
		return categoryRepository.allCategory();
	}

	@Override
	public void renameCategory(CategoryVO vo) {
		categoryRepository.renameCategory(vo);
		
	}

}
