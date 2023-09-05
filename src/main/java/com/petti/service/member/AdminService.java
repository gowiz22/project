package com.petti.service.member;

import java.util.List;

import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;

public interface AdminService {
	public List<MemberVO> findMemeber(String memberId);
	
	public void updateAuth(AuthVO vo);
	
	public List<CategoryVO> categoryList();
	
	public void renameCategory(CategoryVO vo);
}
