package com.petti.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;
import com.petti.repository.free_board.FreeBoardRepository;
import com.petti.repository.free_board.FreeCategoryRepository;
import com.petti.repository.member.AdminRepository;
import com.petti.repository.member.AuthRepository;
import com.petti.service.free_board.FreeBoardService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private FreeCategoryRepository categoryRepository;
	
	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Autowired
	private FreeBoardService boardService;
	
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

	@Override
	public void newCategory(String kind) {
		categoryRepository.newCategory(kind);
	}

	@Override
	public void deleteCategory(Long cno) {
		categoryRepository.removeCategory(cno);
	}

	@Transactional
	@Override
	public void deleteBoard(Long cno) {
		List<Long> bnoList = boardRepository.boardInCategory(cno);
		
		for(Long bno : bnoList) boardService.remove(bno);
	}

	@Override
	public void updateCategory(Long oldCno, Long newCno) {
		repository.changeCategory(oldCno, newCno);
	}

}
