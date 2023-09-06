package com.petti.repository.free_board;

import java.util.List;

import com.petti.domain.free_board.CategoryVO;

public interface FreeCategoryRepository {
	
	List<CategoryVO> allCategory();
	
	void renameCategory(CategoryVO vo);
	
	void newCategory(String kind);
	
	void removeCategory(Long cno);
}
