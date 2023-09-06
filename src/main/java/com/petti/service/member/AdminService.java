package com.petti.service.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.member.AuthVO;
import com.petti.domain.member.MemberVO;

public interface AdminService {
	public List<MemberVO> findMemeber(String memberId);
	
	public void updateAuth(AuthVO vo);
	
	public List<CategoryVO> categoryList();
	
	public void renameCategory(CategoryVO vo);
	
	public void newCategory(String kind);
	
	public void deleteCategory(Long cno);
	
	public void deleteBoard(Long cno);
	
	public void updateCategory(@Param("oldCno") Long oldCno, @Param("newCno") Long newCno);
}
