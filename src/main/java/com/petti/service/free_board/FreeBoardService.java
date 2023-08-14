package com.petti.service.free_board;

import java.util.List;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.free_board.FreeBoardVO;

public interface FreeBoardService {

	List<FreeBoardVO> getList(Criteria criteria); // 게시판 목록
	
	void register(FreeBoardVO vo); // 등록
	
	FreeBoardVO get(Long bno); // 조회
	
	boolean modify(FreeBoardVO vo); // 수정
	
	boolean remove(Long bno); // 삭제
	
	int totalCount(Criteria criteria);
	
	List<CategoryVO> category();
}
